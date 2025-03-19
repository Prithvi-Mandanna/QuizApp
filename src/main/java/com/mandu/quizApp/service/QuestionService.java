package com.mandu.quizApp.service;

import com.mandu.quizApp.repo.QuestionDb;
import com.mandu.quizApp.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDb db;


    public ResponseEntity<List<Question>> getAllQuestions(){

        try {
            return ResponseEntity.status(HttpStatus.OK).body( db.findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body( new ArrayList<>());
    };

    public List<Question> getQuestionsByCategory(String category) {
        return db.findByCategory(category);
    }

    public ResponseEntity<Question> addQuestion(Question question) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(db.save(question)) ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Question());
    };

    public Question updateQuestion(Question question) {
        return db.save(question);
    };

    public void deleteQuestion(int id) {
        db.deleteById(id);
    };
}
