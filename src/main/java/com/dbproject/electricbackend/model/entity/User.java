package com.dbproject.electricbackend.model.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;

@Data
@ApiModel("用户实体")
public class User {
    @Getter
    private final Integer id;

    @Getter
    private final String username;

    @Getter
    private String nickname;
}
