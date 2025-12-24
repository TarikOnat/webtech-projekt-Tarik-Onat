package htw.webtech.todo_app.service;

import htw.webtech.todo_app.entity.Card;
import htw.webtech.todo_app.repository.CardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardService {

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    public List<Card> getCards(Long deckId) {
        return cardRepository.findByDeckId(deckId);
    }

    public Optional<Card> getCardById(Long id) {
        return cardRepository.findById(id);
    }

    public Card createCard(Card card) {
        return cardRepository.save(card);
    }

    public Card updateCard(Long id, Card updatedCard) {
        return cardRepository.findById(id)
                .map(card -> {
                    card.setQuestion(updatedCard.getQuestion());
                    card.setAnswer(updatedCard.getAnswer());
                    card.setLearned(updatedCard.isLearned());
                    return cardRepository.save(card);
                })
                .orElse(null);
    }

    public boolean deleteCard(Long id) {
        if (cardRepository.existsById(id)) {
            cardRepository.deleteById(id);
            return true;
        }
        return false;
    }
}