package com.dbproject.electricbackend.model.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;

@ApiModel("用户注册请求")
@Data
public class UserRegister {
    @Getter
    private final String username;

    @Getter
    private final String password;

    @Getter
    private final String nickname;
}
