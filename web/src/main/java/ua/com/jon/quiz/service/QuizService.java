package ua.com.jon.quiz.service;

import ua.com.jon.quiz.domain.*;
import java.util.List;

/**
 * Created by Олег on 15.01.2016.
 */
public interface QuizService {
    Long create(Quiz quiz);
    Quiz read(Long id);
    boolean update(Quiz quiz);
    boolean delete(Quiz quiz);
    List<Quiz> findAll();
}
