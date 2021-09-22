package com.dbproject.electricbackend.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

@Data
@ApiModel("用户实体")
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
    private String nickname;

    public User(Integer id, String username, String nickname) {
        this.id = id;
        this.username = username;
        this.nickname = nickname;
    }
}
