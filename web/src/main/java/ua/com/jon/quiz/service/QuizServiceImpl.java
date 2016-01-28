package ua.com.jon.quiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.jon.quiz.domain.*;
import ua.com.jon.quiz.dao.*;
import java.util.List;

/**
 * Created by Олег on 15.01.2016.
 */
@Service
@Transactional
public class QuizServiceImpl implements QuizService {
    @Autowired
    private QuizDao quizDao;

    public QuizServiceImpl() {
    }

    public Long create(Quiz quiz) {
        return quizDao.create(quiz);
    }
    @Transactional(readOnly = true)
    public Quiz read(Long id) {
        return quizDao.read(id);
    }

    public boolean update(Quiz quiz) {
        return quizDao.update(quiz);
    }

    public boolean delete(Quiz quiz) {
        return quizDao.delete(quiz);
    }

    @Transactional(readOnly = true)
    public List<Quiz> findAll() {
        return quizDao.findAll();
    }
}
