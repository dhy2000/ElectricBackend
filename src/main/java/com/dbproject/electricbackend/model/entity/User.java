package com.dbproject.electricbackend.model.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

@Data
@ApiModel("用户实体")
public class User {
    @Getter
    @NonNull
    private final Integer id;

    @Getter
    @NonNull
    private final String username;

    @Getter
    @NonNull
    private String nickname;
}
