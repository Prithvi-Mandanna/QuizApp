package com.mandu.quizApp.controller;

import com.mandu.quizApp.service.QuestionService;
import com.mandu.quizApp.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    };


    @GetMapping("category/{category}")
    public List<Question> getQuestionsByCategory(@PathVariable String category){
            return questionService.getQuestionsByCategory(category);
    };

    @PostMapping("add")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);

    };

    //update question
    @PutMapping("update")
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question){
        Question updatedQuestion = questionService.updateQuestion(question);
        return ResponseEntity.status(HttpStatus.OK).body(updatedQuestion);
    };

    //delete question
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable int id){
        questionService.deleteQuestion(id);
        return ResponseEntity.status(HttpStatus.OK).body("Question deleted successfully");
    };

}
