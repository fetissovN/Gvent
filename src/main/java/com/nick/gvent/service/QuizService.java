package com.nick.gvent.service;

import com.nick.gvent.entity.Quiz;

import java.util.List;

public interface QuizService {

    long saveNewQuiz(Quiz quiz);

    Quiz getById(Long id);

    void startQuiz(Quiz quiz);

    void closeQuiz(Quiz quiz);

    void assertQuiz(Quiz quiz);

    List<Quiz> getAllQuiz();

}
