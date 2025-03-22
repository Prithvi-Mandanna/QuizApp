package com.mandu.quizApp.controller;

import com.mandu.quizApp.entity.QuestionWrapper;
import com.mandu.quizApp.entity.Quiz;
import com.mandu.quizApp.entity.Response;
import com.mandu.quizApp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<Quiz> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title  ){
      return quizService.createQuiz(category, numQ, title);
    };

    @GetMapping("get/{quizId}")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsbyQuizId(@PathVariable Integer quizId){
        return quizService.getQuizQuestionsByQuizId(quizId);
    };

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses) {
        return quizService.calculateResult(id, responses);


    }

}
