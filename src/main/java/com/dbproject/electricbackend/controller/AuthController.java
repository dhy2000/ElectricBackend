package com.dbproject.electricbackend.controller;

import com.dbproject.electricbackend.http.exception.CustomException;
import com.dbproject.electricbackend.http.exception.ExceptionDefine;
import com.dbproject.electricbackend.http.postbody.UserLogin;
import com.dbproject.electricbackend.http.response.LoginSuccess;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "用户身份验证相关 API")
@RequestMapping("/auth")
@RestController
public class AuthController {

    @ApiOperation("用户登录(该接口目前无实际作用)")
    @PostMapping(value = "login")
    public LoginSuccess login(@RequestBody UserLogin login) throws CustomException {
        // 先来个简单版
        if ("admin".equals(login.getUsername())
            && "e10adc3949ba59abbe56e057f20f883e".equals(login.getPassword())) {
            return new LoginSuccess("xxxxxxxxxxxx");
        } else {
            throw CustomException.fromDefinedTable(ExceptionDefine.WRONG_USERNAME_PASSWORD);
        }
    }

    @ApiOperation("用户注销")
    @PostMapping(value = "logout")
    public int logout() {
        return 0;
    }
}
