package com.mandu.quizApp.service;

import com.mandu.quizApp.entity.Question;
import com.mandu.quizApp.entity.QuestionWrapper;
import com.mandu.quizApp.entity.Quiz;
import com.mandu.quizApp.entity.Response;
import com.mandu.quizApp.repo.QuestionDb;
import com.mandu.quizApp.repo.QuizDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestionsByQuizId(Integer quizId) {
        Optional<Quiz> quiz = quizDao.findById(quizId);
        List<Question> questionsFromDB = quiz.get().getQuestionList();
        List<QuestionWrapper> questionsForUsers = new ArrayList<>();
        for (Question q:questionsFromDB){
            QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionsForUsers.add(qw);
        }
        try {
            return ResponseEntity.status(HttpStatus.OK).body(questionsForUsers);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    };

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Optional<Quiz> quiz = quizDao.findById(id);
        if (quiz.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(0); // Quiz not found
        }

        List<Question> questionsFromDb = quiz.get().getQuestionList();
        int score = 0;

        // Create a map of question IDs to questions for quick lookup
        Map<Integer, Question> questionMap = new HashMap<>();
        for (Question question : questionsFromDb) {
            questionMap.put(question.getId(), question);
        }

        // Iterate through the responses and compare with the correct answers
        for (Response response : responses) {
            Question question = questionMap.get(response.getId()); // Get the question by ID
            if (question != null && response.getResponse() != null) {
                if (response.getResponse().equals(question.getCorrectAnswer())) {
                    score++;
                }
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(score);
    }
}
