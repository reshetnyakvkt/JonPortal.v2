package ua.com.jon.quiz.dao;

import ua.com.jon.quiz.domain.*;

import java.util.List;

/**
 * Created by Reshetnyak Viktor on 28.01.2016.
 * Package ua.com.jon.quiz.dao
 */
public interface QuizUserSprintDao {
    Long create(QuizUserSprint quizUserSprint);
    QuizUserSprint read(Long id);
    boolean update(QuizUserSprint quizUserSprint);
    boolean delete(QuizUserSprint quizUserSprint);
    List<QuizUserSprint> findAll();
}
