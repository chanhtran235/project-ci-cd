package com.example.demo.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class HandleGlobalException {
//    @ExceptionHandler(NoResourceFoundException.class)
//    public String handleNoResource(){
//        return "404";
//    }
}
