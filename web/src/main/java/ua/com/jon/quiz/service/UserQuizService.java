package ua.com.jon.quiz.service;

        import ua.com.jon.quiz.domain.*;
        import ua.com.jon.quiz.dao.*;


import java.util.List;

/**
 * Created by Олег on 16.01.2016.
 */
public interface UserQuizService {
    Long create(UserQuiz userQuiz);
    UserQuiz read(Long id);
    boolean update(UserQuiz userQuiz);
    boolean delete(UserQuiz userQuiz);
    List<UserQuiz> findAll();
}
