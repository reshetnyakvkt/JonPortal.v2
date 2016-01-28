package ua.com.jon.quiz.dao;

import ua.com.jon.quiz.domain.*;

import java.util.List;

/**
 * Created by Олег on 16.01.2016.
 */
public interface UserQuizAnswerDao {
    Long create(UserQuizAnswer userQuizAnswer);
    UserQuizAnswer read(Long id);
    boolean update(UserQuizAnswer userQuizAnswer);
    boolean delete(UserQuizAnswer userQuizAnswer);
    List<UserQuizAnswer> findAll();
}
