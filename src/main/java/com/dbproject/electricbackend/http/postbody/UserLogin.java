package com.dbproject.electricbackend.http.postbody;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

@Data
public class UserLogin {
    @Getter
    @NonNull
    private final String username;

    @Getter
    @NonNull
    private final String password;    // md5 encrypted
}
