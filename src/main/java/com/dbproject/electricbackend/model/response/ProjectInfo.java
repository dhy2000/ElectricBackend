package com.dbproject.electricbackend.model.response;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

@ApiModel("工程信息返回")
@Data
public class ProjectInfo {
    @Getter
    @NonNull
    private final String name = "Electric";

    @Getter
    @NonNull
    private final String course = "Principles of Database System";

    @Getter
    private final int year = 2021;
}