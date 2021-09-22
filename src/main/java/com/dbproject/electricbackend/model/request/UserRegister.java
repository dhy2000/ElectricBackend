package com.dbproject.electricbackend.model.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

@ApiModel("用户注册请求")
@Data
public class UserRegister {
    @Getter
    @NonNull
    private final String username;

    @Getter
    @NonNull
    private final String password;

    @Getter
    @NonNull
    private final String nickname;
}
