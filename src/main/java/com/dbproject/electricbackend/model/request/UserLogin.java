package com.dbproject.electricbackend.model.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

@ApiModel("用户登录请求")
@Data
public class UserLogin {
    @Getter
    @NonNull
    private final String username;

    @Getter
    @NonNull
    private final String password;    // md5 encrypted
}
