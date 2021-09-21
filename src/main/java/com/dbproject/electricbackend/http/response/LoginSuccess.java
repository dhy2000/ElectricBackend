package com.dbproject.electricbackend.http.response;

import lombok.Data;
import lombok.Getter;

@Data
public class LoginSuccess {
    @Getter
    private final String token;

    public LoginSuccess(String token) {
        this.token = token;
    }
}
