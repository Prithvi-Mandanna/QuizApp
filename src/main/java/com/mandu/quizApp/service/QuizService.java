package com.mandu.quizApp.service;

import com.mandu.quizApp.entity.Question;
import com.mandu.quizApp.entity.Quiz;
import com.mandu.quizApp.repo.QuestionDb;
import com.mandu.quizApp.repo.QuizDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDb questionDao;


    public ResponseEntity<Quiz> createQuiz(String category, int numQ, String title) {

        List<Question> questionList = questionDao.findRandomQuestionsByCategory(category, numQ);


        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionList(questionList);
        return ResponseEntity.status(HttpStatus.CREATED).body(quizDao.save(quiz));
    };
}
