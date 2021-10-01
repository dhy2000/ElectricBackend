package com.dbproject.electricbackend.schema;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

import java.util.Date;

@Data
@ApiModel("用户实体")
@AllArgsConstructor
public class User {
    @Getter
    @NonNull
    @ApiModelProperty("用户编号(唯一且自动递增)")
    private final Integer id;

    @Getter
    @NonNull
    @ApiModelProperty("用户名(唯一)")
    private final String username;

    @Getter
    @NonNull
    @ApiModelProperty("用户昵称")
    private final String nickname;

    @Getter
    @NonNull
    @ApiModelProperty("个性签名")
    private final String signature;

    @Getter
    @NonNull
    @ApiModelProperty("出生日期")
    private final Date birthday;

    @Getter
    @ApiModelProperty("电子邮箱")
    private final String email;

    @Getter
    @ApiModelProperty("电话")
    private final String phone;

    @Getter
    @NonNull
    @ApiModelProperty("余额")
    private final Integer balance;
}
