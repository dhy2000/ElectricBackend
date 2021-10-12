package com.dbproject.electricbackend.schema;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

@Data
@ApiModel("修改用户密码请求体")
public class PasswordUpdate {
    @Getter
    @NonNull
    @ApiModelProperty("旧密码")
    private final String oldPassword;

    @Getter
    @NonNull
    @ApiModelProperty("新密码")
    private final String newPassword;
}
