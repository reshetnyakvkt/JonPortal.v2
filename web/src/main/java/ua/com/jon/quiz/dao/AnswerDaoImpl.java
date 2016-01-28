package ua.com.jon.quiz.dao;

import ua.com.jon.quiz.domain.*;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
/**
 * Created by Reshetnyak Viktor on 19.01.2016.
 * Project quiz
 * Package quiz.dao
 */
@Repository("answerDao")
@Transactional
public class AnswerDaoImpl implements AnswerDao {
    private static Logger log = Logger.getLogger(AnswerDaoImpl.class);

    @Autowired(required = true)
    private SessionFactory factory;

    public AnswerDaoImpl() {
    }

    @Override
    public Long create(Answer answer) {
        return (Long) factory.getCurrentSession().save(answer);
    }

    @Override
    public Answer read(Long id) {
        return (Answer) factory.getCurrentSession().get(Answer.class, id);
    }

    @Override
    public boolean update(Answer answer) {
        try{
            factory.getCurrentSession().update(answer);
            return true;
        } catch (Exception ex){
            log.info(ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Answer answer) {
        try{
            factory.getCurrentSession().delete(answer);
            return true;
        } catch (Exception ex){
            log.info(ex.getMessage());
        }
        return false;
    }

    @Override
    public List<Answer> findAll() {
        return factory.getCurrentSession().createQuery("FROM Answer").list();
    }


}

