package htw.webtech.todo_app;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Card {
    private Long id;
    private String question;
    private String answer;

    // Konstruktoren
    public Card(Long id, String question, String answer) {
        this.id = id;
        this.question = question;
        this.answer = answer;
    }

}
