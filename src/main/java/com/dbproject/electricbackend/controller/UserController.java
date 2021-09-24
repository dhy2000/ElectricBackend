package com.dbproject.electricbackend.controller;

import com.dbproject.electricbackend.auth.AuthRequired;
import com.dbproject.electricbackend.auth.TokenUtil;
import com.dbproject.electricbackend.exception.CustomException;
import com.dbproject.electricbackend.model.entity.User;
import com.dbproject.electricbackend.model.request.UserRegister;
import com.dbproject.electricbackend.model.response.StatusMessage;
import com.dbproject.electricbackend.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
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
    public StatusMessage userRegister(@RequestBody UserRegister register) throws SQLException, ClassNotFoundException {
        userService.addUser(register);
        return StatusMessage.successfulStatus();
    }

    @ApiOperation("获取当前存在的全部用户")
    @GetMapping("userlist")
    public List<User> userList() throws SQLException, ClassNotFoundException {
        return userService.getAllUsers();
    }

    @ApiOperation("查看当前登录用户信息")
    @GetMapping("whoami")
    @AuthRequired
    public User whoAmI(@RequestHeader("Token") String token)
            throws SQLException, CustomException, ClassNotFoundException {
        int userId = TokenUtil.verifyTokenAndGetUserId(token);
        return userService.getUserById(userId);
    }
}
