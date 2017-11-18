package com.nick.gvent.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ExceptionController {
    @RequestMapping("/404.html")
    public String render404(Model model) {
        // Add model attributes
        return "/error/404.html";
    }
}
