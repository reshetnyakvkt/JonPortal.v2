package ua.com.jon.quiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.com.jon.quiz.domain.Quiz;
import ua.com.jon.quiz.domain.UserQuiz;
import ua.com.jon.quiz.service.QuizService;
import ua.com.jon.quiz.service.UserQuizService;


import java.util.List;

/**
 * Created by Олег on 15.01.2016.
 */
@RestController
public class QuizController {
    @Autowired
    private QuizService quizService;
    @Autowired
    private UserQuizService userQuizService;




    @RequestMapping(value = "/getQuiz/{id}", method = RequestMethod.GET)
    public Quiz goToQuiz(@PathVariable("id") long id) {
        return quizService.read(id);
    }

    @RequestMapping(value = "/getUserResults", method = RequestMethod.GET)
    public List<UserQuiz> getUserResults(@RequestParam Long userId) {
        System.out.println(userId);
        List<UserQuiz> resList = userQuizService.findResultByUser(userId);
        System.out.println(resList);
        return resList;
    }




    @RequestMapping(value = "/submitUserQuiz", method = RequestMethod.POST)
    public Long createQuizResult(@RequestBody UserQuiz userQuiz) {
        System.out.println(userQuiz.getDate());
        Long uqId;
        try {
            uqId = userQuizService.create(userQuiz);
            return uqId;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }



}
