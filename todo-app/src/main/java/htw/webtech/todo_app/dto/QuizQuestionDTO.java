package htw.webtech.todo_app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuizQuestionDTO {
    private Long cardId;
    private String question;
    private QuizType type;

    private List<String> options;

    private String displayedAnswer;
}