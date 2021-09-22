package com.dbproject.electricbackend.http.response;

import lombok.Data;
import lombok.Getter;

@Data
public class StatusMessage {
    @Getter
    private final int code;

    @Getter
    private final String message;

    public StatusMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Getter
    private static final StatusMessage successfulStatus = new StatusMessage(0, "");
}
