package htw.webtech.todo_app.service;

import htw.webtech.todo_app.entity.Deck;
import htw.webtech.todo_app.repository.DeckRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeckService {

    private final DeckRepository deckRepository;

    public DeckService(DeckRepository deckRepository) {
        this.deckRepository = deckRepository;
    }

    public List<Deck> getAllDecks() {
        return deckRepository.findAll();
    }

    public Optional<Deck> getDeckById(Long id) {
        return deckRepository.findById(id);
    }

    public Deck createDeck(Deck deck) {
        return deckRepository.save(deck);
    }

    public Optional<Deck> updateDeck(Long id, String title) {
        return deckRepository.findById(id)
                .map(deck -> {
                    deck.setTitle(title);
                    return deckRepository.save(deck);
                });
    }

    public boolean deleteDeck(Long id) {
        if (deckRepository.existsById(id)) {
            deckRepository.deleteById(id);
            return true;
        }
        return false;
    }
}