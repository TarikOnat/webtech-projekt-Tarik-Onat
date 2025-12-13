package htw.webtech.todo_app.service;

import htw.webtech.todo_app.entity.Deck;
import htw.webtech.todo_app.repository.DeckRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DeckService {

    private final DeckRepository deckRepository;

    public DeckService(DeckRepository deckRepository) {
        this.deckRepository = deckRepository;
    }

    public List<Deck> getAllDecks() {
        return deckRepository.findAll();
    }

    public Deck createDeck(Deck deck) {
        return deckRepository.save(deck);
    }

    public void deleteDeck(Long id) {
        deckRepository.deleteById(id);
    }
}

