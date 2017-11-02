package com.nick.gvent.controllers;

import com.nick.gvent.entity.Quiz;
import com.nick.gvent.service.QuizServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/** LayoutController class for UI
 * @autor Fetissov Mikalai
 * @version 1.0
 */

@Controller
public class LayoutController {

    @Autowired
    private QuizServiceImpl quizService;


    @RequestMapping("/")
    public String main(){
        return "Main";
    }

    @RequestMapping("/map")
    public String map(){
        return "MapEvent";
    }

    @RequestMapping("/quiz/{id}")
    public String quizLook(@PathVariable Long id, Model model){
        if (id != null){
            Quiz quiz = quizService.getById(id);
            if (quiz != null){
                model.addAttribute("quiz", quiz);
            }
        }
        return "quiz";
    }
}
