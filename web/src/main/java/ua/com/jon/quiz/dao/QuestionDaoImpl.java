package ua.com.jon.quiz.dao;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.jon.quiz.domain.*;
import java.util.List;
/**
 * Created by Reshetnyak Viktor on 19.01.2016.
  * Package quiz.dao
 */
@Repository("questionDao")
@Transactional
public class QuestionDaoImpl implements QuestionDao {
    private static Logger log = Logger.getLogger(QuestionDaoImpl.class);

    @Autowired(required = true)
    private SessionFactory factory;

    public QuestionDaoImpl() {
    }

    @Override
    public Long create(Question question) {
        return (Long) factory.getCurrentSession().save(question);
    }

    @Override
    public Question read(Long id) {
        return (Question) factory.getCurrentSession().get(Question.class, id);
    }

    @Override
    public boolean update(Question question) {
        try{
            factory.getCurrentSession().update(question);
            return true;
        } catch (Exception ex){
            log.info(ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Question question) {
        try{
            factory.getCurrentSession().delete(question);
            return true;
        } catch (Exception ex){
            log.info(ex.getMessage());
        }
        return false;
    }

    @Override
    public List<Question> findAll() {
        return factory.getCurrentSession().createQuery("FROM Question").list();
    }
}
