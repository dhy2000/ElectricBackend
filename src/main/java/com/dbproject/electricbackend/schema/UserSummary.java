package com.dbproject.electricbackend.schema;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

@Data
@ApiModel("用户简略信息")
public class UserSummary {
    @Getter
    @NonNull
    @ApiModelProperty("用户 id")
    private final Integer id;

    @Getter
    @NonNull
    @ApiModelProperty("用户名(唯一)")
    private final String username;

    @Getter
    @NonNull
    @ApiModelProperty("昵称")
    private final String nickname;
}
