package ua.com.jon.quiz.dao;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.jon.quiz.domain.QuizUserSprint;
import java.util.List;

/**
 * Created by Reshetnyak Viktor on 28.01.2016.
 * Package ua.com.jon.quiz.dao
 */
@Repository("quizUserSprintDao")
@Transactional
public class QuizUserSprintDaoImpl implements QuizUserSprintDao {
    private static Logger log = Logger.getLogger(QuizUserSprintDaoImpl.class);

    @Autowired(required = true)
    private SessionFactory factory;

    public QuizUserSprintDaoImpl() {
    }

    @Override
    public Long create(QuizUserSprint quizUserSprint) {
        return (Long) factory.getCurrentSession().save(quizUserSprint);
    }

    @Override
    public QuizUserSprint read(Long id) {
        return (QuizUserSprint) factory.getCurrentSession().get(QuizUserSprint.class, id);
    }

    @Override
    public boolean update(QuizUserSprint quizUserSprint) {
        try{
            factory.getCurrentSession().update(quizUserSprint);
            return true;
        } catch (Exception ex){
            log.info(ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(QuizUserSprint quizUserSprint) {
        try{
            factory.getCurrentSession().delete(quizUserSprint);
            return true;
        } catch (Exception ex){
            log.info(ex.getMessage());
        }
        return false;
    }

    @Override
    public List<QuizUserSprint> findAll() {
        return factory.getCurrentSession().createQuery("FROM QuizUserSprint").list();
    }
}
