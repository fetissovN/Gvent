//package com.nick.gvent.controllers;
//
//import org.hibernate.service.spi.ServiceException;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.NoHandlerFoundException;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
////@EnableWebMvc
//@ControllerAdvice
//public class ServiceExceptionHandler extends ResponseEntityExceptionHandler{
//
//    @ExceptionHandler(NoHandlerFoundException.class)
//    public ModelAndView handleError404(HttpServletRequest request, Exception e)   {
//        ModelAndView mav = new ModelAndView("404");
//        mav.addObject("exception", e);
//        //mav.addObject("errorcode", "404");
//        return mav;
//    }
////
////    @ExceptionHandler(NoHandlerFoundException.class)
////    public ModelAndView handleError404(HttpServletRequest request, Exception e)   {
////        Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Request: " + request.getRequestURL() + " raised " + e);
////        return new ModelAndView("404");
////    }
//
////    @Override
////    protected ResponseEntity<Object> handle   NoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
////                                                                   HttpStatus status, WebRequest request) {
////        Map<String,String> responseBody = new HashMap<>();
////        responseBody.put("path",request.getContextPath());
////        responseBody.put("message","The URL you have reached is not in service at this time (404).");
////        return new ResponseEntity<Object>(responseBody, HttpStatus.NOT_FOUND);
////    }
//
////    @ExceptionHandler(Throwable.class)
////    @ResponseBody
////    ResponseEntity<Object> handleControllerException(HttpServletRequest request, Throwable ex) {
////        HttpStatus status = getStatus(request);
////        return new ResponseEntity<Object>(status);
////    }
//
//    private HttpStatus getStatus(HttpServletRequest request) {
//        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
//        if (statusCode == null) {
//            return HttpStatus.INTERNAL_SERVER_ERROR;
//        }
//        return HttpStatus.valueOf(statusCode);
//    }
//}
