package ua.com.jon.quiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.jon.quiz.domain.*;
import ua.com.jon.quiz.dao.*;
import java.util.List;

/**
 * Created by Олег on 16.01.2016.
 */
@Service
@Transactional
public class UserQuizServiceImpl implements UserQuizService {

    @Autowired
    private UserQuizDao userQuizDao;

    public UserQuizServiceImpl() {
    }

    public Long create(UserQuiz userQuiz) {
        return userQuizDao.create(userQuiz);
    }
    @Transactional(readOnly = true)
    public UserQuiz read(Long id) {
        return userQuizDao.read(id);
    }

    public boolean update(UserQuiz userQuiz) {
        return userQuizDao.update(userQuiz);
    }

    public boolean delete(UserQuiz userQuiz) {
        return userQuizDao.delete(userQuiz);
    }

    @Transactional(readOnly = true)
    public List<UserQuiz> findAll() {
        return userQuizDao.findAll();
    }

    @Transactional(readOnly = true)
    public List<UserQuiz> findResultByUser(Long userId) {
        return userQuizDao.findResultByUser(userId);
    }

}
