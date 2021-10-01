package com.dbproject.electricbackend.schema;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@ApiModel("项目基本信息")
@Data
@NoArgsConstructor
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
