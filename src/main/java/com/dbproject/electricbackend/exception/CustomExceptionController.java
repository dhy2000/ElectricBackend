package com.dbproject.electricbackend.exception;

import com.dbproject.electricbackend.schema.StatusMessage;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionController {

    @ResponseBody
    @ExceptionHandler(CustomException.class)
    public StatusMessage responseCustomException(CustomException e) {
        return StatusMessage.fromCustomException(e);
    }

    @ResponseBody
    @ExceptionHandler(Throwable.class)
    public StatusMessage responseCatch(Throwable th) {
        return StatusMessage.fromThrowable(th);
    }
}
