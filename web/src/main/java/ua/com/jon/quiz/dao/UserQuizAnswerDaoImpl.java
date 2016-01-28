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
 * Created by Олег on 16.01.2016.
 */
@Repository
public class UserQuizAnswerDaoImpl implements UserQuizAnswerDao {
    private static Logger log = Logger.getLogger(UserQuizAnswerDaoImpl.class);

    @Autowired
    private SessionFactory factory;

    public UserQuizAnswerDaoImpl() {
    }

    public SessionFactory getFactory() {
        return factory;
    }

    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }

    public Long create(UserQuizAnswer userQuizAnswer) {
        return (Long)factory.getCurrentSession().save(userQuizAnswer);
    }

    public UserQuizAnswer read(Long id) {
        return (UserQuizAnswer) factory.getCurrentSession().get(UserQuizAnswer.class, id);
    }

    public boolean update(UserQuizAnswer UserQuizAnswer) {
        try {
            factory.getCurrentSession().update(UserQuizAnswer);
            return true;
        } catch (HibernateException e) {
            log.error("Open session failed", e);
        }
        return false;
    }

    public boolean delete(UserQuizAnswer userQuizAnswer) {
        try {
            factory.getCurrentSession().delete(userQuizAnswer);
            return true;
        } catch (HibernateException e) {
            log.error("Open session failed", e);
        }
        return false;
    }

    public List<UserQuizAnswer> findAll() {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from UserQuizAnswer");
        return query.list();
    }

}
