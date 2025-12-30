package service_test;

import htw.webtech.todo_app.entity.Card;
import htw.webtech.todo_app.repository.CardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import htw.webtech.todo_app.service.CardService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CardServiceTest {

    @Mock
    private CardRepository cardRepository;

    @InjectMocks
    private CardService cardService;

    private Card testCard;

    @BeforeEach
    void setUp() {
        testCard = new Card();
        testCard.setId(1L);
        testCard.setQuestion("Was ist Java?");
        testCard.setAnswer("Eine Programmiersprache");
        testCard.setLearned(false);
    }

    @Test
    void getAllCards_shouldReturnAllCards() {
        List<Card> cards = Arrays.asList(testCard, new Card());
        when(cardRepository.findAll()).thenReturn(cards);

        List<Card> result = cardService.getAllCards();

        assertEquals(2, result.size());
        verify(cardRepository, times(1)).findAll();
    }

    @Test
    void getCardById_shouldReturnCard_whenExists() {
        when(cardRepository.findById(1L)).thenReturn(Optional.of(testCard));

        Optional<Card> result = cardService.getCardById(1L);

        assertTrue(result.isPresent());
        assertEquals("Was ist Java?", result.get().getQuestion());
    }

    @Test
    void getCardById_shouldReturnEmpty_whenNotExists() {
        when(cardRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Card> result = cardService.getCardById(99L);

        assertFalse(result.isPresent());
    }

    @Test
    void createCard_shouldSaveAndReturnCard() {
        when(cardRepository.save(any(Card.class))).thenReturn(testCard);

        Card result = cardService.createCard(testCard);

        assertNotNull(result);
        assertEquals("Was ist Java?", result.getQuestion());
        verify(cardRepository, times(1)).save(testCard);
    }

    @Test
    void updateCard_shouldUpdateAndReturnCard_whenExists() {
        Card updatedCard = new Card();
        updatedCard.setQuestion("Neue Frage");
        updatedCard.setAnswer("Neue Antwort");
        updatedCard.setLearned(true);

        when(cardRepository.findById(1L)).thenReturn(Optional.of(testCard));
        when(cardRepository.save(any(Card.class))).thenReturn(testCard);

        Card result = cardService.updateCard(1L, updatedCard);

        assertNotNull(result);
        verify(cardRepository, times(1)).save(any(Card.class));
    }

    @Test
    void updateCard_shouldReturnNull_whenNotExists() {
        when(cardRepository.findById(99L)).thenReturn(Optional.empty());

        Card result = cardService.updateCard(99L, testCard);

        assertNull(result);
    }

    @Test
    void deleteCard_shouldReturnTrue_whenExists() {
        when(cardRepository.existsById(1L)).thenReturn(true);
        doNothing().when(cardRepository).deleteById(1L);

        boolean result = cardService.deleteCard(1L);

        assertTrue(result);
        verify(cardRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteCard_shouldReturnFalse_whenNotExists() {
        when(cardRepository.existsById(99L)).thenReturn(false);

        boolean result = cardService.deleteCard(99L);

        assertFalse(result);
        verify(cardRepository, never()).deleteById(any());
    }
}
