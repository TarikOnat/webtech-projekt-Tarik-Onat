package htw.webtech.todo_app.service;

import htw.webtech.todo_app.dto.*;
import htw.webtech.todo_app.entity.Card;
import htw.webtech.todo_app.repository.CardRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class QuizService {

    private final CardRepository cardRepository;
    private int questionCounter = 0;

    public QuizService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public QuizQuestionDTO generateNextQuestion(Long deckId) {
        List<Card> cards = cardRepository.findByDeckId(deckId);

        if (cards.isEmpty()) {
            return null;
        }

        Card card = cards.get(new Random().nextInt(cards.size()));

        QuizType type = getNextQuizType();
        questionCounter++;

        return switch (type) {
            case MULTIPLE_CHOICE -> generateMultipleChoiceQuestion(card, cards);
            case TRUE_FALSE -> generateTrueFalseQuestion(card, cards);
            case FREE_TEXT -> generateFreeTextQuestion(card);
        };
    }


    private QuizType getNextQuizType() {
        return switch (questionCounter % 3) {
            case 0 -> QuizType.MULTIPLE_CHOICE;
            case 1 -> QuizType.TRUE_FALSE;
            default -> QuizType.FREE_TEXT;
        };
    }


    private QuizQuestionDTO generateMultipleChoiceQuestion(Card correctCard, List<Card> allCards) {
        List<String> options = new ArrayList<>();
        options.add(correctCard.getAnswer());

        List<Card> otherCards = allCards.stream()
                .filter(c -> !c.getId().equals(correctCard.getId()))
                .collect(Collectors.toList());

        Collections.shuffle(otherCards);
        otherCards.stream()
                .limit(3)
                .forEach(c -> options.add(c.getAnswer()));

        while (options.size() < 4) {
            options.add("Option " + (options.size() + 1));
        }

        Collections.shuffle(options);

        QuizQuestionDTO dto = new QuizQuestionDTO();
        dto.setCardId(correctCard.getId());
        dto.setQuestion(correctCard.getQuestion());
        dto.setType(QuizType.MULTIPLE_CHOICE);
        dto.setOptions(options);
        return dto;
    }


    private QuizQuestionDTO generateTrueFalseQuestion(Card correctCard, List<Card> allCards) {
        boolean showCorrectAnswer = new Random().nextBoolean();

        String displayedAnswer;
        if (showCorrectAnswer) {
            displayedAnswer = correctCard.getAnswer();
        } else {
            List<Card> otherCards = allCards.stream()
                    .filter(c -> !c.getId().equals(correctCard.getId()))
                    .collect(Collectors.toList());

            if (!otherCards.isEmpty()) {
                displayedAnswer = otherCards.get(new Random().nextInt(otherCards.size())).getAnswer();
            } else {
                displayedAnswer = "Falsche Antwort";
            }
        }

        QuizQuestionDTO dto = new QuizQuestionDTO();
        dto.setCardId(correctCard.getId());
        dto.setQuestion(correctCard.getQuestion());
        dto.setType(QuizType.TRUE_FALSE);
        dto.setDisplayedAnswer(displayedAnswer);
        return dto;
    }


    private QuizQuestionDTO generateFreeTextQuestion(Card card) {
        QuizQuestionDTO dto = new QuizQuestionDTO();
        dto.setCardId(card.getId());
        dto.setQuestion(card.getQuestion());
        dto.setType(QuizType.FREE_TEXT);
        return dto;
    }


    public QuizResultDTO checkAnswer(QuizAnswerDTO answerDTO) {
        Optional<Card> cardOpt = cardRepository.findById(answerDTO.getCardId());

        if (cardOpt.isEmpty()) {
            return new QuizResultDTO(false, "", "Card nicht gefunden");
        }

        Card card = cardOpt.get();
        String correctAnswer = card.getAnswer();
        String userAnswer = answerDTO.getUserAnswer();

        boolean isCorrect = switch (answerDTO.getType()) {
            case MULTIPLE_CHOICE -> correctAnswer.equalsIgnoreCase(userAnswer);

            case TRUE_FALSE -> {
                boolean userSaysTrue = userAnswer.equalsIgnoreCase("true");

                boolean displayedWasCorrect = answerDTO.getDisplayedAnswer() != null
                        && answerDTO.getDisplayedAnswer().equals(correctAnswer);

                yield userSaysTrue == displayedWasCorrect;
            }

            case FREE_TEXT -> isFreeTextCorrect(userAnswer, correctAnswer);
        };

        String explanation = isCorrect ? "Richtig! ✅" : "Falsch! ❌ Richtige Antwort: " + correctAnswer;

        return new QuizResultDTO(isCorrect, correctAnswer, explanation);
    }


    private boolean isFreeTextCorrect(String userAnswer, String correctAnswer) {
        String cleanUser = userAnswer.trim().toLowerCase();
        String cleanCorrect = correctAnswer.trim().toLowerCase();

        if (cleanUser.equals(cleanCorrect)) {
            return true;
        }

        int distance = levenshteinDistance(cleanUser, cleanCorrect);
        int maxLength = Math.max(cleanUser.length(), cleanCorrect.length());
        double similarity = 1.0 - (double) distance / maxLength;

        return similarity >= 0.8;
    }


    private int levenshteinDistance(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];

        for (int i = 0; i <= a.length(); i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= b.length(); j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                int cost = (a.charAt(i - 1) == b.charAt(j - 1)) ? 0 : 1;
                dp[i][j] = Math.min(Math.min(
                                dp[i - 1][j] + 1,
                                dp[i][j - 1] + 1),
                        dp[i - 1][j - 1] + cost
                );
            }
        }

        return dp[a.length()][b.length()];
    }
}