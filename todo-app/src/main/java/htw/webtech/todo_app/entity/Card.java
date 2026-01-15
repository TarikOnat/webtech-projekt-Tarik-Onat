package htw.webtech.todo_app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Frage darf nicht leer sein")
    @Size(min = 3, message = "Frage muss mindestens 3 Zeichen lang sein")
    private String question;

    @NotBlank(message = "Antwort darf nicht leer sein")
    @Size(min = 1, message = "Antwort darf nicht leer sein")
    private String answer;

    private boolean learned = false;

    @ManyToOne
    @JoinColumn(name = "deck_id")
    @JsonBackReference
    private Deck deck;
}