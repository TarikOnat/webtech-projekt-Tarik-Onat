package htw.webtech.todo_app;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:5173", "https://dein-frontend.onrender.com"})
@RestController
public class CardController {

    private List<Card> cards = new ArrayList<>(List.of(
            new Card(1L, "Was ist 5 x 5?", "25"),
            new Card(2L, "Hauptstadt von Deutschland?", "Berlin"),
            new Card(3L, "Wofür steht HTW?", "Hochschule für Technik und Wirtschaft")
    ));

    @GetMapping("/api/cards")
    public List<Card> getCards() {
        return cards;
    }

    @PostMapping("/api/cards")
    public Card createCard(@RequestBody Card newCard) {
        newCard.setId((long) (cards.size() + 1));
        cards.add(newCard);
        return newCard;
    }
}
