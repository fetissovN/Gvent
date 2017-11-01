package com.nick.voter.service;

import com.nick.voter.dao.RemindRepository;
import com.nick.voter.entity.Quiz;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/** Services for main CRUD operations
 * @autor Fetissov Mikalai
 * @version 1.0
 */

@Service
public class QuizServiceImpl implements QuizService {

    private final Logger LOGGER= LoggerFactory.getLogger(QuizServiceImpl.class);


    @Autowired
    private RemindRepository repository;

    @Override
    public long saveNewQuiz(Quiz quiz) {
        Quiz newQuiz = repository.saveAndFlush(quiz);
        LOGGER.info("Save new quiz id " + newQuiz.getId());
        return newQuiz.getId();
    }

    @Override
    public Quiz getById(Long id) {
        LOGGER.info("Get quiz by id " + id);
        return repository.findOne(id);
    }

    @Override
    public void startQuiz(Quiz quiz) {
        quiz.setStarted(1);
        Quiz quizDB = repository.save(quiz);
        LOGGER.info("Start quiz id " + quizDB.getId());
    }

    @Override
    public void closeQuiz(Quiz quiz) {
        quiz.setClosed(1);
        repository.save(quiz);
        LOGGER.info("Close quiz id " + quiz.getId());
    }

    @Override
    public void assertQuiz(Quiz quiz) {
        quiz.setVotes(quiz.getVotes()+1);
        repository.save(quiz);
        LOGGER.info("Plus one vote to quiz id " + quiz.getId());
    }

    @Override
    public List<Quiz> getAllQuiz() {
        LOGGER.info("Get all quiz");
        return repository.findAll();
    }
}
