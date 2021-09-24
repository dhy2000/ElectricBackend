package com.dbproject.electricbackend.controller;

import com.dbproject.electricbackend.exception.CustomException;
import com.dbproject.electricbackend.exception.ExceptionDefine;
import com.dbproject.electricbackend.model.request.UserLogin;
import com.dbproject.electricbackend.model.response.LoginSuccess;
import com.dbproject.electricbackend.model.response.StatusMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "身份验证")
@RequestMapping("/auth")
@RestController
public class AuthController {

    @ApiOperation("用户登录(该接口目前无实际作用)")
    @PostMapping(value = "login")
    public LoginSuccess login(@RequestBody UserLogin login) throws CustomException {
        // 先来个简单版
        if ("admin".equals(login.getUsername())
            && "e10adc3949ba59abbe56e057f20f883e".equals(login.getPassword())) {
            return new LoginSuccess("^^^^^^NOT IMPLEMENTED^^^^^^^^^");
        } else {
            throw CustomException.fromDefinedTable(ExceptionDefine.WRONG_USERNAME_PASSWORD);
        }
    }

    @ApiOperation("用户注销")
    @PostMapping(value = "logout")
    public StatusMessage logout() {
        return StatusMessage.successfulStatus();
    }
}
