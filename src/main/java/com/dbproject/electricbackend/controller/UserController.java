package com.dbproject.electricbackend.controller;

import com.dbproject.electricbackend.http.postbody.UserRegister;
import com.dbproject.electricbackend.http.response.StatusMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("用户操作")
@RequestMapping("/user")
@RestController
public class UserController {

    @ApiOperation("用户注册")
    @PostMapping("register")
    public StatusMessage userRegister(UserRegister register) {

        return StatusMessage.getSuccessfulStatus();
    }

}
