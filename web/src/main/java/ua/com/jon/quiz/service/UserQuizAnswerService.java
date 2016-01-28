package ua.com.jon.quiz.service;

import ua.com.jon.quiz.domain.*;

import java.util.List;

/**
 * Created by Reshetnyak Viktor on 28.01.2016.
 * Package ua.com.jon.quiz.service
 */
public interface UserQuizAnswerService {
    Long create(UserQuizAnswer userQuizAnswer);
    UserQuizAnswer read(Long id);
    boolean update(UserQuizAnswer userQuizAnswer);
    boolean delete(UserQuizAnswer userQuizAnswer);
    List<UserQuizAnswer> findAll();
}
