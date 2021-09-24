package com.dbproject.electricbackend.controller;

import com.dbproject.electricbackend.auth.AuthRequired;
import com.dbproject.electricbackend.auth.TokenUtil;
import com.dbproject.electricbackend.model.request.UserLogin;
import com.dbproject.electricbackend.model.response.LoginSuccess;
import com.dbproject.electricbackend.model.response.StatusMessage;
import com.dbproject.electricbackend.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "身份验证")
@RequestMapping("/auth")
@RestController
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation("用户登录")
    @PostMapping(value = "login")
    public LoginSuccess login(@RequestBody UserLogin login) throws Exception {
        int userId = userService.loginAndGetId(login);
        String token = TokenUtil.createToken(userId, login.getPassword());
        return new LoginSuccess(token);
    }

    @ApiOperation("用户注销")
    @PostMapping(value = "logout")
    @AuthRequired
    public StatusMessage logout(@RequestHeader("Token") String token) {
        TokenUtil.cancelToken(token);
        return StatusMessage.successfulStatus();
    }
}
