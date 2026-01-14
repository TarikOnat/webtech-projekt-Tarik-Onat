package htw.webtech.todo_app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuizAnswerDTO {
    private Long cardId;
    private String userAnswer;
    private QuizType type;
}