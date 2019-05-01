package com.nick.gvent.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/** LayoutController class for UI
 * @autor Fetissov Mikalai
 * @version 1.0
 */

@Controller
public class LayoutController {


    @RequestMapping("/")
    public String main(){
        return "dashboard";
    }

    @RequestMapping("/map")
    public String map(){
        return "mapEvent";
    }

    public void method1(){
        System.out.println("one");

    }
    public void method_add(){
        System.out.println("add");
    }

//    @RequestMapping("/error")
//    public String error(Model model){
//        model.addAttribute("ex","eee");
//        return "404";
//    }
}
