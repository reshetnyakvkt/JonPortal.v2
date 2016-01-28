package ua.com.jon.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 12.01.15
 */

@Controller
public class GameController {
    public static final String GAME_VIEW_NAME = "game";

    @RequestMapping(value = "/game", method = RequestMethod.GET)
    public String requestMethodGet() {
        return GAME_VIEW_NAME;
    }
}
