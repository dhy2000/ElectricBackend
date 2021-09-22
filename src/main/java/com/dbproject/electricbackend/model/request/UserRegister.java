package com.dbproject.electricbackend.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

@ApiModel("用户注册请求")
@Data
public class UserRegister {
    @Getter
    @NonNull
    @ApiModelProperty("用户名(必须唯一, 不超过 32 字符)")
    private final String username;

    @Getter
    @NonNull
    @ApiModelProperty("密码(MD5加密后)")
    private final String password;

    @Getter
    @NonNull
    @ApiModelProperty("用户昵称(不超过 64 字符)")
    private final String nickname;
}
