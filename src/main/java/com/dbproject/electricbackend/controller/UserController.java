package com.dbproject.electricbackend.controller;

import com.dbproject.electricbackend.auth.AuthRequired;
import com.dbproject.electricbackend.auth.TokenUtil;
import com.dbproject.electricbackend.exception.CustomException;
import com.dbproject.electricbackend.schema.User;
import com.dbproject.electricbackend.schema.RegisterRequest;
import com.dbproject.electricbackend.schema.StatusMessage;
import com.dbproject.electricbackend.schema.UserSummary;
import com.dbproject.electricbackend.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Api(tags = "用户")
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
    public StatusMessage userRegister(@RequestBody RegisterRequest register) throws SQLException, ClassNotFoundException {
        userService.addUser(register);
        return StatusMessage.successfulStatus();
    }

    @ApiOperation("获取当前存在的全部用户")
    @GetMapping("userlist")
    public List<UserSummary> userList() throws SQLException, ClassNotFoundException {
        return userService.getUserList();
    }

    @ApiOperation("查看当前登录用户的简略信息")
    @GetMapping("whoami")
    @AuthRequired
    public UserSummary whoAmI(@RequestHeader("Token") String token)
            throws SQLException, CustomException, ClassNotFoundException {
        int userId = TokenUtil.verifyTokenAndGetUserId(token);
        return userService.getUserSummaryById(userId);
    }

    @ApiOperation("查看当前用户的完整信息")
    @GetMapping("userinfo")
    @AuthRequired
    public User userInfo(@RequestHeader("Token") String token)
            throws CustomException, SQLException, ClassNotFoundException {
        int userId = TokenUtil.verifyTokenAndGetUserId(token);
        return userService.getUserById(userId);
    }
}
