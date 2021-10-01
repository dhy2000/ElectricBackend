package com.dbproject.electricbackend.schema;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

@ApiModel("用户登录请求")
@Data
public class LoginRequest {
    @Getter
    @NonNull
    @ApiModelProperty("用户名")
    private final String username;

    @Getter
    @NonNull
    @ApiModelProperty("密码")
    private final String password;
}
