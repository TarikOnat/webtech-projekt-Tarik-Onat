package htw.webtech.todo_app.controller;

import htw.webtech.todo_app.entity.Card;
import htw.webtech.todo_app.service.CardService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping
    public List<Card> getAllCards() {
        return cardService.getAllCards();
    }

    @GetMapping("/deck/{deckId}")
    public List<Card> getCards(@PathVariable Long deckId) {
        return cardService.getCards(deckId);
    }

    @PostMapping
    public Card createCard(@Valid @RequestBody Card card) {
        return cardService.createCard(card);
    }

    @PutMapping("/{id}")
    public Card updateCard(@PathVariable Long id, @Valid @RequestBody Card card) {
        return cardService.updateCard(id, card);
    }

    @DeleteMapping("/{id}")
    public void deleteCard(@PathVariable Long id) {
        cardService.deleteCard(id);
    }
}