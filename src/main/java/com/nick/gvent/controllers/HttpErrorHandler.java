package com.nick.gvent.controllers;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HttpErrorHandler implements ErrorController {
    String path = "/error";

    @RequestMapping(value="/400")
    public String error400(Model model){
        model.addAttribute("ex", "Bad request");
        return path+"/400";
    }

    @RequestMapping(value="/404")
    public String error404(Model model){
        model.addAttribute("ex", "Page not found");
        return path+"/404";
    }

    @RequestMapping(value="/500")
    public String error500(Model model){
        model.addAttribute("ex", "Bad gateway");
        return path+"/500";
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String err(HttpServletResponse response, Model model){
        Integer status = response.getStatus();
        if (status == 404){
            error404(model);
        }
        if (status >500){
            error500(model);
        }
        model.addAttribute("ex","page not found");
        return "404";
    }

    @Override
    public String getErrorPath() {
        return path;
    }
}
