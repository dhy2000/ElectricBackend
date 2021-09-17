package com.dbproject.electricbackend.entity;

import lombok.Data;
import lombok.Getter;

@Data
public class ErrorMessage {
    @Getter
    private final int code;

    @Getter
    private final String message;

    public ErrorMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
