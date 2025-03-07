package com.mandu.quizApp.service;

import com.mandu.quizApp.repo.QuestionDb;
import com.mandu.quizApp.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDb db;

    public List<Question> getAllQuestions(){
        return db.findAll();
    };

    public List<Question> getQuestionsByCategory(String category) {
        return db.findByCategory(category);
    }

    public Question addQuestion(Question question) {
      return db.save(question);
    };
}
