package com.dbproject.electricbackend.schema;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

@ApiModel("登录成功后 Token 返回")
@Data
@AllArgsConstructor
public class TokenAssign {
    @Getter
    @NonNull
    private final String token;
}
