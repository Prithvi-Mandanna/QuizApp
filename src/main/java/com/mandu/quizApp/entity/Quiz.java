package com.mandu.quizApp.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.apache.catalina.LifecycleState;

import java.util.List;

@Entity
@Data
public class Quiz {
    @Id
    @jakarta.persistence.GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer quizId;
    private String title;
    @ManyToMany
    @JoinTable(
            name = "quiz_questions",
            joinColumns = @JoinColumn(name = "quiz_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id")
    )
    private List<Question> questionList;

    public Quiz(Integer quizId, String title, List<Question> questionList) {
        this.quizId = quizId;
        this.title = title;
        this.questionList = questionList;
    }

    public Quiz() {
    }

    public Integer getQuizId() {
        return quizId;
    }

    public void setQuizId(Integer quizId) {
        this.quizId = quizId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }
}
