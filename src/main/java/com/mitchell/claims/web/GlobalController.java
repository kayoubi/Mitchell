package com.mitchell.claims.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Khaled Ayoubi
 */
@ControllerAdvice
public class GlobalController {
    public static final String ERROR = "ERROR";
    public static final String MARSHALLING = "MARSHALLING";
    public static final String VALIDATION = "VALIDATION";

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public @ResponseBody String handleException(Exception e) {
        e.printStackTrace();
        return ERROR;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public @ResponseBody String handleMarshallingException(HttpMessageNotReadableException e) throws Exception {
        return MARSHALLING;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public @ResponseBody String handleValidationException(MethodArgumentNotValidException e) throws Exception {
        return VALIDATION;
    }
}
