package htw.webtech.todo_app.service;

import htw.webtech.todo_app.entity.Card;
import htw.webtech.todo_app.repository.CardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public List<Card> getCards(Long deckId) {
        return cardRepository.findByDeckId(deckId);
    }

    public Card createCard(Card card) {
        return cardRepository.save(card);
    }

    public Card updateCard(Long id, Card cardData) {
        Card card = cardRepository.findById(id).orElseThrow();

        card.setQuestion(cardData.getQuestion());
        card.setAnswer(cardData.getAnswer());
        card.setLearned(cardData.isLearned());

        return cardRepository.save(card);
    }

    public void deleteCard(Long id) {
        cardRepository.deleteById(id);
    }
}

