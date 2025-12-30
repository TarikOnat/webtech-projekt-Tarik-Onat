package service_test;

import htw.webtech.todo_app.entity.Deck;
import htw.webtech.todo_app.repository.DeckRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import htw.webtech.todo_app.service.DeckService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeckServiceTest {

    @Mock
    private DeckRepository deckRepository;

    @InjectMocks
    private DeckService deckService;

    private Deck testDeck;

    @BeforeEach
    void setUp() {
        testDeck = new Deck();
        testDeck.setId(1L);
        testDeck.setTitle("Java Grundlagen");
    }

    @Test
    void getAllDecks_shouldReturnAllDecks() {
        List<Deck> decks = Arrays.asList(testDeck, new Deck());
        when(deckRepository.findAll()).thenReturn(decks);

        List<Deck> result = deckService.getAllDecks();

        assertEquals(2, result.size());
        verify(deckRepository, times(1)).findAll();
    }

    @Test
    void getDeckById_shouldReturnDeck_whenExists() {
        when(deckRepository.findById(1L)).thenReturn(Optional.of(testDeck));

        Optional<Deck> result = deckService.getDeckById(1L);

        assertTrue(result.isPresent());
        assertEquals("Java Grundlagen", result.get().getTitle());
    }

    @Test
    void getDeckById_shouldReturnEmpty_whenNotExists() {
        when(deckRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Deck> result = deckService.getDeckById(99L);

        assertFalse(result.isPresent());
    }

    @Test
    void createDeck_shouldSaveAndReturnDeck() {
        when(deckRepository.save(any(Deck.class))).thenReturn(testDeck);

        Deck result = deckService.createDeck(testDeck);

        assertNotNull(result);
        assertEquals("Java Grundlagen", result.getTitle());
        verify(deckRepository, times(1)).save(testDeck);
    }

    @Test
    void updateDeck_shouldUpdateAndReturnDeck_whenExists() {
        when(deckRepository.findById(1L)).thenReturn(Optional.of(testDeck));
        when(deckRepository.save(any(Deck.class))).thenReturn(testDeck);

        Optional<Deck> result = deckService.updateDeck(1L, "Neuer Titel");

        assertTrue(result.isPresent());
        verify(deckRepository, times(1)).save(any(Deck.class));
    }

    @Test
    void updateDeck_shouldReturnEmpty_whenNotExists() {
        when(deckRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Deck> result = deckService.updateDeck(99L, "Neuer Titel");

        assertFalse(result.isPresent());
    }

    @Test
    void deleteDeck_shouldReturnTrue_whenExists() {
        when(deckRepository.existsById(1L)).thenReturn(true);
        doNothing().when(deckRepository).deleteById(1L);

        boolean result = deckService.deleteDeck(1L);

        assertTrue(result);
        verify(deckRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteDeck_shouldReturnFalse_whenNotExists() {
        when(deckRepository.existsById(99L)).thenReturn(false);

        boolean result = deckService.deleteDeck(99L);

        assertFalse(result);
        verify(deckRepository, never()).deleteById(any());
    }
}
