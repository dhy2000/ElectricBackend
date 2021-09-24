package com.dbproject.electricbackend.model.response;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

@ApiModel("登录成功后 Token 返回")
@Data
@AllArgsConstructor
public class LoginSuccess {
    @Getter
    @NonNull
    private final String token;
}
