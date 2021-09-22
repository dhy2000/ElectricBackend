package com.dbproject.electricbackend.http.postbody;

import lombok.Data;
import lombok.Getter;

@Data
public class UserRegister {
    @Getter
    private final String username;

    @Getter
    private final String password;

    @Getter
    private final String nickname;
}
