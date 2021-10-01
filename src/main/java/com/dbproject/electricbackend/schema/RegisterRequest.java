package com.dbproject.electricbackend.schema;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

import java.util.Date;

@ApiModel("用户注册请求")
@Data
public class RegisterRequest {
    @Getter
    @NonNull
    @ApiModelProperty("用户名(必须唯一, 不超过 32 字符)")
    private final String username;

    @Getter
    @NonNull
    @ApiModelProperty("密码")
    private final String password;

    @Getter
    @NonNull
    @ApiModelProperty("用户昵称(不超过 64 字符)")
    private final String nickname;

    @Getter
    @NonNull
    @ApiModelProperty("出生日期, 建议格式: YYYY-MM-DD，示例: 1970-01-01")
    private final Date birthday;

    @Getter
    @ApiModelProperty("电子邮箱")
    private final String email;

    @Getter
    @ApiModelProperty("电话")
    private final String phone;
}
