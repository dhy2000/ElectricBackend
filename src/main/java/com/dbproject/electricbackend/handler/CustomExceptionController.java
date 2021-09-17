package com.dbproject.electricbackend.handler;

import com.dbproject.electricbackend.http.exception.CustomException;
import com.dbproject.electricbackend.http.response.ErrorMessage;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionController {

    @ResponseBody
    @ExceptionHandler(CustomException.class)
    public ErrorMessage responseCustomException(CustomException e) {
        return new ErrorMessage(e.getCode(), e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ErrorMessage responseException (Exception e) {
        return new ErrorMessage(9999, "Exception caught: " + e.getClass().getSimpleName());
    }
}
