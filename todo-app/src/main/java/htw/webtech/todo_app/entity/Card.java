package htw.webtech.todo_app.entity;

import jakarta.persistence.*;
        import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;
    private String answer;

    private boolean learned = false;

    @ManyToOne
    @JoinColumn(name = "deck_id")
    private Deck deck;
}
