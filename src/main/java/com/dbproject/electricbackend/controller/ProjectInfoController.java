package com.dbproject.electricbackend.controller;

import com.dbproject.electricbackend.entity.ProjectInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "一组简单的示例 API")
@RequestMapping("/info")
@RestController
public class ProjectInfoController {

    @ApiOperation("获取本工程的基本信息")
    @GetMapping(value = "project", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiResponse(code = 200, message = "JSON 格式的工程信息")
    public ProjectInfo getProjectInfo() {
        return new ProjectInfo();
    }
}
