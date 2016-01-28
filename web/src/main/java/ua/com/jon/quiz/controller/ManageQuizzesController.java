package ua.com.jon.quiz.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.com.jon.quiz.service.QuizService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Reshetnyak Viktor on 25.01.2016.
 * Package quiz.controller
 */
@Controller
public class ManageQuizzesController {
    public static final Logger log = Logger.getLogger(ManageQuizzesController.class);

    @Autowired(required = true)
    private QuizService formService;

    public ManageQuizzesController() {
    }

    @RequestMapping(value = "/manageQuizzes", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        log.info("/manageQuizzes");

//        try {
            HttpSession session = request.getSession(true);
//            if (!authService.authenticate((String)session.getAttribute("login"))){
//                return "index";
//            }
            session.setAttribute("quizzes", formService.findAll());
            session.setAttribute("quiz_test", "TEST-OK!");
//
//        } catch (AuthenticationException e) {
//            e.printStackTrace();
//        }
        return "/manageQuizzes";
    }

    @RequestMapping(value = "/j_quizzes", method = {RequestMethod.GET})
    public @ResponseBody
    List<Object> j_quizzes(Model model) {
        log.info("Start j_quizzes(Model model)-->");

        List<Object> res = new ArrayList<>();

        res.add(formService.findAll());
        log.info("<--End res.get(0): " + res.get(0));
        return res;
    }

    @RequestMapping(value = "/j_quiz_del", method = {RequestMethod.GET})
    public @ResponseBody
    List<Object> j_quiz_del(@RequestParam Long id, Model model) {
        log.info("Start j_quiz_del(id=" + id + ", Model model)-->");

        List<Object> res = new ArrayList<>();

        res.add(formService.delete(formService.read(id)));
        log.info("<--End j_quiz_del(id=" +  + id + ")");
        return res;
    }

}
