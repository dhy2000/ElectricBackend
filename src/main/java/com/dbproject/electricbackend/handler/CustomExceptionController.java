package com.dbproject.electricbackend.handler;

import com.dbproject.electricbackend.http.exception.CustomException;
import com.dbproject.electricbackend.http.response.StatusMessage;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionController {

    @ResponseBody
    @ExceptionHandler(CustomException.class)
    public StatusMessage responseCustomException(CustomException e) {
        return new StatusMessage(e.getCode(), e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public StatusMessage responseException (Exception e) {
        return new StatusMessage(9999, "Exception caught: " + e.getClass().getSimpleName());
    }
}
