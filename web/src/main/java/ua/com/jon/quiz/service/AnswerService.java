package ua.com.jon.quiz.service;

import ua.com.jon.quiz.domain.*;
import java.util.List;

/**
 * Created by Reshetnyak Viktor on 20.01.2016.
 * Package quiz.service
 */
public interface AnswerService {
    Long create(Answer answer);
    List<Answer> findAll();
}
