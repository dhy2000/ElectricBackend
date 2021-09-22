package com.dbproject.electricbackend.controller;

import com.dbproject.electricbackend.model.entity.User;
import com.dbproject.electricbackend.model.request.UserRegister;
import com.dbproject.electricbackend.model.response.StatusMessage;
import com.dbproject.electricbackend.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "用户操作接口")
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService service) {
        this.userService = service;
    }

    @ApiOperation("用户注册")
    @PostMapping("register")
    public StatusMessage userRegister(UserRegister register) {
        userService.addUser(register);
        return StatusMessage.getSuccessfulStatus();
    }

    @ApiOperation("获取当前存在的全部用户")
    @GetMapping("userlist")
    public List<User> userList() {
        return userService.getAllUsers();
    }
}
