package com.dbproject.electricbackend.model.response;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;

@ApiModel("登录成功后 Token 返回")
@Data
public class LoginSuccess {
    @Getter
    private final String token;

    public LoginSuccess(String token) {
        this.token = token;
    }
}
