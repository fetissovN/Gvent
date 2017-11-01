package com.nick.voter.service;

import com.nick.voter.entity.Quiz;

import java.util.List;

public interface QuizService {

    long saveNewQuiz(Quiz quiz);

    Quiz getById(Long id);

    void startQuiz(Quiz quiz);

    void closeQuiz(Quiz quiz);

    void assertQuiz(Quiz quiz);

    List<Quiz> getAllQuiz();

}
