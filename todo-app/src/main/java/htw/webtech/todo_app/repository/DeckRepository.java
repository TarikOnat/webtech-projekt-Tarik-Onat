package htw.webtech.todo_app.repository;

import htw.webtech.todo_app.entity.Deck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeckRepository extends JpaRepository<Deck, Long> {
}

