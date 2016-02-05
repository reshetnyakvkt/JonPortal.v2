package ua.com.jon.quiz.dao;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.jon.quiz.domain.*;
import ua.com.jon.quiz.dao.*;

import java.util.List;

/**
 * Created by Олег on 16.01.2016.
 */
@Repository
public class UserQuizDaoImpl implements UserQuizDao {
    private static Logger log = Logger.getLogger(UserQuizDaoImpl.class);

    @Autowired
    private SessionFactory factory;

    public UserQuizDaoImpl() {
    }

    public SessionFactory getFactory() {
        return factory;
    }

    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }

    public Long create(UserQuiz userQuiz) {
        return (Long)factory.getCurrentSession().save(userQuiz);
    }

    public UserQuiz read(Long id) {
        return (UserQuiz) factory.getCurrentSession().get(UserQuiz.class, id);
    }

    public boolean update(UserQuiz userQuiz) {
        try {
            factory.getCurrentSession().update(userQuiz);
            return true;
        } catch (HibernateException e) {
            log.error("Open session failed", e);
        }
        return false;
    }

    public boolean delete(UserQuiz userQuiz) {
        try {
            factory.getCurrentSession().delete(userQuiz);
            return true;
        } catch (HibernateException e) {
            log.error("Open session failed", e);
        }
        return false;
    }

    public List<UserQuiz> findAll() {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from UserQuiz");
        return query.list();
    }

    public List<UserQuiz> findResultByUser(Long userId) {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from UserQuiz as uq where uq.user.id =:userId");
        query.setParameter("userId", userId);
        return query.list();
    }
}
