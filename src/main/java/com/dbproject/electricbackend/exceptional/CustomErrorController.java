package com.dbproject.electricbackend.exceptional;

import com.dbproject.electricbackend.model.response.StatusMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@Api(tags = "自定义的全局错误响应接口")
@RequestMapping("/")
@RestController
public class CustomErrorController implements ErrorController {

    private static final String PATH = "/error";

    @ApiOperation("HTTP 请求发生错误时返回错误信息")
    @RequestMapping(value = PATH, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public StatusMessage errorHandler(HttpServletResponse response) {
        int status = response.getStatus();
        return new StatusMessage(status, "HTTP Error with code " + status);
    }
}
