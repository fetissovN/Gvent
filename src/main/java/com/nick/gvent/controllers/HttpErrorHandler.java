package com.nick.gvent.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;


@Controller
public class HttpErrorHandler implements ErrorController {

    @Value("${error.handle.path}")
    private String PATH;

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String err(HttpServletResponse response, Model model){
        Integer status = response.getStatus();
        if (status == 404){
            model.addAttribute("ex","Page not found 404");
        }else if (status >500){
            model.addAttribute("ex","Bad request 5XX");
        }else if(status == 405){
            model.addAttribute("ex","Not Allowed 405");
        }else {
            model.addAttribute("ex","Fuck off");
        }
        return PATH+"/errorPage";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
