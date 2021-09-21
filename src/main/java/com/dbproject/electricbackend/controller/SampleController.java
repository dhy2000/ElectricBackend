package com.dbproject.electricbackend.controller;

import com.dbproject.electricbackend.http.exception.CustomException;
import com.dbproject.electricbackend.http.response.ProjectInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import lombok.Data;
import lombok.Getter;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "一组简单的示例 API")
@RequestMapping("/sample")
@RestController
public class SampleController {

    @ApiOperation("获取本工程的基本信息")
    @GetMapping(value = "project", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ProjectInfo getProjectInfo() {
        return new ProjectInfo();
    }

    @Data
    private static class SumOfTwoSrc {
        @Getter
        private final int src1;
        @Getter
        private final int src2;
        @Getter
        private final int sum;

        public SumOfTwoSrc(int src1, int src2, int sum) {
            this.src1 = src1;
            this.src2 = src2;
            this.sum = sum;
        }
    }

    @ApiOperation("一个测试的 GET 接口")
    @GetMapping(value = "calc", produces = {MediaType.APPLICATION_JSON_VALUE})
    public SumOfTwoSrc calc(
        @ApiParam("操作数1") @RequestParam("src1") String src1,
        @ApiParam("操作数2") @RequestParam("src2") String src2
    ) throws CustomException {
        try {
            int value1 = Integer.parseInt(src1);
            int value2 = Integer.parseInt(src2);
            int sum = value1 + value2;
            return new SumOfTwoSrc(value1, value2, sum);
        } catch (NumberFormatException e) {
            throw CustomException.customized("Wrong number format!");
        }
    }
}
