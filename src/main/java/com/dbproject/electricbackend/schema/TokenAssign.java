package com.dbproject.electricbackend.schema;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

@ApiModel("Token 分配")
@Data
@AllArgsConstructor
public class TokenAssign {
    @Getter
    @NonNull
    @ApiModelProperty("用来标识用户登录身份的 Token，以后访问需要登录的 API 需在请求头中附加 \"Token\" 字段")
    private final String token;
}
