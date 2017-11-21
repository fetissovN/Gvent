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

//    @RequestMapping(value="/400")
//    public String error400(Model model){
//        model.addAttribute("ex", "Bad request");
//        return path+"/400";
//    }
//
//    @RequestMapping(value="/404")
//    public String error404(Model model){
//        model.addAttribute("ex", "Page not found");
//        return path+"/404";
//    }
//
//    @RequestMapping(value="/500")
//    public String error500(Model model){
//        model.addAttribute("ex", "Bad gateway");
//        return path+"/500";
//    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String err(HttpServletResponse response, Model model){
        Integer status = response.getStatus();
        if (status == 404){
            model.addAttribute("ex","Page not found 404");
        }else if (status >500){
            model.addAttribute("ex","Bad request 5XX");
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
