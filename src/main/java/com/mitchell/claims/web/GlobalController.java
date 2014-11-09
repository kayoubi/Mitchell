package com.mitchell.claims.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Khaled Ayoubi
 */
@ControllerAdvice
public class GlobalController {
    @ExceptionHandler(Exception.class)
    public @ResponseBody String handleException(Exception e) throws Exception {
        e.printStackTrace();
        throw e;
    }
}
