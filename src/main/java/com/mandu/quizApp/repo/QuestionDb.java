package com.mandu.quizApp.repo;

import com.mandu.quizApp.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDb extends JpaRepository<Question, Integer> {


    List<Question> findByCategory(String category);


    @Query(value = "select * from question q where q.Category = :category order by RAND() LIMIT :numQ", nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String category, int numQ);
}
