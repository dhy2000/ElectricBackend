package com.dbproject.electricbackend.schema;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;

@Data
@ApiModel("添加或删除好友操作")
public class FriendRequest {
    @Getter
    @ApiModelProperty("对方的用户名")
    private String username;
}
