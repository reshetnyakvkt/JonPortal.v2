package ua.com.jon.quiz.service;

import org.springframework.stereotype.Service;
import ua.com.jon.quiz.domain.*;
import java.util.List;

/**
 * Created by Олег on 15.01.2016.
 */
public interface QuestionService {
    Long create(Question question);
    Question read(Long id);
    boolean update(Question question);
    boolean delete(Question question);
    List<Question> findAll();
}
