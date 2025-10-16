package htw.webtech.todo_app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class CardController {

    @GetMapping("/api/cards")
    public List<Card> getAllCards() {
        return List.of(
                new Card(1L, "Was ist 5x5?", "25"),
                new Card(2L, "Was ist die Hauptstadt von Deutschland?", "Berlin")
        );
    }
}