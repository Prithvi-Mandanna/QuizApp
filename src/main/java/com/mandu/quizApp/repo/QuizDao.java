package com.mandu.quizApp.repo;

import com.mandu.quizApp.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizDao extends JpaRepository <Quiz, Integer>{
}
