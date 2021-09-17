package com.dbproject.electricbackend.http.exception;

import lombok.Getter;

public class CustomException extends Exception {
    @Getter
    private final int code;
    @Getter
    private final String message;

    public CustomException(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
