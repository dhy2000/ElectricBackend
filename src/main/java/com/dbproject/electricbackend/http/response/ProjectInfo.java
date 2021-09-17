package com.dbproject.electricbackend.http.response;

import lombok.Data;
import lombok.Getter;

@Data
public class ProjectInfo {
    @Getter
    private final String name = "Electric";

    @Getter
    private final String course = "Principles of Database System";

    @Getter
    private final int year = 2021;
}
