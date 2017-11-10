package com.nick.gvent.controllers;

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


    @RequestMapping("/")
    public String main(){
        return "Dashboard";
    }

    @RequestMapping("/map")
    public String map(){
        return "MapEvent";
    }

//    @RequestMapping("/quiz/{id}")
//    public String quizLook(@PathVariable Long id, Model model){
//        if (id != null){
//            Quiz quiz = quizService.getById(id);
//            if (quiz != null){
//                model.addAttribute("quiz", quiz);
//            }
//        }
//        return "quiz";
//    }
}
