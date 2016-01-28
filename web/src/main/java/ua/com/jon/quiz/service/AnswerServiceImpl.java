package ua.com.jon.quiz.service;

import ua.com.jon.quiz.domain.*;
import ua.com.jon.quiz.dao.*;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Locale;


/**
 * Created by Reshetnyak Viktor on 20.01.2016.
 * Project quiz
 * Package quiz.service
 */
@Service("answerService")
@Transactional
public class AnswerServiceImpl implements AnswerService {
    public static final Logger log = Logger.getLogger(AnswerServiceImpl.class);

    @Autowired(required = true)
    private AnswerDao answerDao;

    public AnswerServiceImpl() {
        Locale.setDefault(Locale.ENGLISH);
    }

    @Override
    public Long create(Answer answer) {
        return answerDao.create(answer);
    }

    @Override
    public List<Answer> findAll() {
        return answerDao.findAll();
    }
}
