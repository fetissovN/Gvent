package com.nick.gvent.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Nick on 11/6/2017.
 */
@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLogin(@RequestParam(value = "param", required = false) String param,
                           @RequestParam(value = "logout", required = false) String logout,
                           Model model) {

        model.addAttribute("param", param);
        model.addAttribute("logout", logout);
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String setLogin(@RequestParam(value = "param", required = false) String param,
                           @RequestParam(value = "logout", required = false) String logout,
                           Model model) {

        model.addAttribute("param", param);
        model.addAttribute("logout", logout);
        return "login";
    }

}
