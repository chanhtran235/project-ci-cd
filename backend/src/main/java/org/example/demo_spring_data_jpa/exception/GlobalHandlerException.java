package org.example.demo_spring_data_jpa.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class GlobalHandlerException {
    @ExceptionHandler(NoResourceFoundException.class)
    public String handle(){
        return "404";
    }
}
