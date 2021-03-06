package com.dbproject.electricbackend.controller;

import com.dbproject.electricbackend.schema.ProjectInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "API 示例")
@RequestMapping("/sample")
@RestController
public class SampleController {
    @ApiOperation("获取本工程的基本信息")
    @GetMapping(value = "project", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ProjectInfo getProjectInfo() {
        return new ProjectInfo();
    }
}
