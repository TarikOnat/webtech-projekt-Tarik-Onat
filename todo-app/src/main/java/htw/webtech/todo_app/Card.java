package htw.webtech.todo_app;

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

    // Getter
    public Long getId() { return id; }
    public String getQuestion() { return question; }
    public String getAnswer() { return answer; }
}
