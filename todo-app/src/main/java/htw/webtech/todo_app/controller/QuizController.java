package htw.webtech.todo_app.controller;

import htw.webtech.todo_app.dto.QuizAnswerDTO;
import htw.webtech.todo_app.dto.QuizQuestionDTO;
import htw.webtech.todo_app.dto.QuizResultDTO;
import htw.webtech.todo_app.service.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }


    @GetMapping("/deck/{deckId}/next")
    public ResponseEntity<QuizQuestionDTO> getNextQuestion(@PathVariable Long deckId) {
        QuizQuestionDTO question = quizService.generateNextQuestion(deckId);

        if (question == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(question);
    }


    @PostMapping("/check")
    public ResponseEntity<QuizResultDTO> checkAnswer(@RequestBody QuizAnswerDTO answerDTO) {
        QuizResultDTO result = quizService.checkAnswer(answerDTO);
        return ResponseEntity.ok(result);
    }
}
