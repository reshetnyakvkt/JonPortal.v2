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
public class QuestionServiceImpl implements QuestionService{
    @Autowired
    private QuestionDao questionDao;

    public QuestionServiceImpl() {
    }

    public Long create(Question question) {
        return questionDao.create(question);
    }
    @Transactional(readOnly = true)
    public Question read(Long id) {
        return questionDao.read(id);
    }

    public boolean update(Question question) {
        return questionDao.update(question);
    }

    public boolean delete(Question question) {
        return questionDao.delete(question);
    }

    @Transactional(readOnly = true)
    public List<Question> findAll() {
        return questionDao.findAll();
    }
}
