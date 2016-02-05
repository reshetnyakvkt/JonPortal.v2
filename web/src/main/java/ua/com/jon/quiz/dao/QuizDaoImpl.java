package ua.com.jon.quiz.dao;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.jon.quiz.domain.*;
import java.util.List;

/**
 * Created by Олег on 15.01.2016.
 */
@Repository
public class QuizDaoImpl implements QuizDao {
    private static Logger log = Logger.getLogger(QuizDaoImpl.class);

    @Autowired
    private SessionFactory factory;

    public QuizDaoImpl() {
    }

    public SessionFactory getFactory() {
        return factory;
    }

    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }

    public Long create(Quiz quiz) {
        return (Long)factory.getCurrentSession().save(quiz);
    }

    public Quiz read(Long id) {
        return (Quiz) factory.getCurrentSession().get(Quiz.class, id);
    }

    public boolean update(Quiz quiz) {
        try {
            factory.getCurrentSession().update(quiz);
            return true;
        } catch (HibernateException e) {
            log.error("Open session failed", e);
        }
        return false;
    }

    public boolean delete(Quiz quiz) {
        try {
            factory.getCurrentSession().delete(quiz);
            return true;

        } catch (HibernateException e) {
            log.error("Open session failed", e);
        }
        return false;
    }

    public List<Quiz> findAll() {
        return factory.getCurrentSession().createQuery("FROM Quiz").list();
    }
}
