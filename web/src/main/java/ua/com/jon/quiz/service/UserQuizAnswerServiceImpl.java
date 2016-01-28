package ua.com.jon.quiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.jon.quiz.dao.*;
import ua.com.jon.quiz.domain.*;
import java.util.List;

/**
 * Created by Reshetnyak Viktor on 28.01.2016.
 * Package ua.com.jon.quiz.service
 */
@Service
@Transactional
public class UserQuizAnswerServiceImpl {
    @Autowired
    private UserQuizAnswerDao userQuizAnswerDao;

    public UserQuizAnswerServiceImpl() {
    }

    public Long create(UserQuizAnswer userQuizAnswer) {
        return userQuizAnswerDao.create(userQuizAnswer);
    }
    @Transactional(readOnly = true)
    public UserQuizAnswer read(Long id) {
        return userQuizAnswerDao.read(id);
    }

    public boolean update(UserQuizAnswer userQuizAnswer) {
        return userQuizAnswerDao.update(userQuizAnswer);
    }

    public boolean delete(UserQuizAnswer userQuizAnswer) {
        return userQuizAnswerDao.delete(userQuizAnswer);
    }

    @Transactional(readOnly = true)
    public List<UserQuizAnswer> findAll() {
        return userQuizAnswerDao.findAll();
    }
}
