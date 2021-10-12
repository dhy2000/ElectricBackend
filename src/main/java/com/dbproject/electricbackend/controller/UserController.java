package com.dbproject.electricbackend.controller;

import com.dbproject.electricbackend.auth.AuthRequired;
import com.dbproject.electricbackend.auth.TokenUtil;
import com.dbproject.electricbackend.exception.CustomException;
import com.dbproject.electricbackend.schema.*;
import com.dbproject.electricbackend.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public StatusMessage userRegister(@RequestBody RegisterRequest register)
            throws SQLException, ClassNotFoundException {
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
    public UserProfile userInfo(@RequestHeader("Token") String token)
            throws CustomException, SQLException, ClassNotFoundException {
        int userId = TokenUtil.verifyTokenAndGetUserId(token);
        return userService.getUserProfileById(userId);
    }

    @ApiOperation("用户充值")
    @PostMapping("recharge")
    @AuthRequired
    public StatusMessage recharge(@RequestHeader("Token") String token,
                                  @RequestBody RechargeRequest recharge)
            throws CustomException, SQLException, ClassNotFoundException {
        int userId = TokenUtil.verifyTokenAndGetUserId(token);
        userService.recharge(userId, recharge.getAmount());
        return StatusMessage.successfulStatus();
    }

    @ApiOperation("查看余额")
    @GetMapping("balance")
    @AuthRequired
    public int balance(@RequestHeader("Token") String token)
            throws CustomException, SQLException, ClassNotFoundException {
        int userId = TokenUtil.verifyTokenAndGetUserId(token);
        return userService.getBalance(userId);
    }

    @ApiOperation("获取头像 URL")
    @GetMapping("avatar")
    @ApiResponse(code = 200, message = "图片的 URL 地址")
    public String getAvatarUrl(@ApiParam("用户 ID") @RequestParam("user") int userId)
            throws SQLException, ClassNotFoundException, CustomException {
        return userService.getAvatar(userId);
    }

    @ApiOperation("设置头像, 请求体格式为 multipart/form-data，参数 KEY 为 \"file\", VALUE 为图片文件")
    @PostMapping("setAvatar")
    @AuthRequired
    public StatusMessage setAvatar(@RequestHeader("Token") String token, @RequestParam("file") MultipartFile image)
            throws SQLException, CustomException, ClassNotFoundException {
        int userId = TokenUtil.verifyTokenAndGetUserId(token);
        userService.setAvatar(userId, image);
        return StatusMessage.successfulStatus();
    }

    @ApiOperation("修改个人信息, 请求体内容代表更新后的个人信息 (原始值需要先获取个人信息)")
    @PostMapping("updateInfo")
    @AuthRequired
    public StatusMessage updateInfo(@RequestHeader("Token") String token, @RequestBody UserProfileUpdate profile)
            throws CustomException, SQLException, ClassNotFoundException {
        int userId = TokenUtil.verifyTokenAndGetUserId(token);
        if (profile.getId() != userId) {
            throw CustomException.defined(CustomException.Define.INVALID_SESSION);
        }
        userService.updateProfile(profile);
        return StatusMessage.successfulStatus();
    }

    @ApiOperation("修改密码")
    @PostMapping("setPassword")
    @AuthRequired
    public StatusMessage setPassword(@RequestHeader("Token") String token,
                                     @RequestBody PasswordUpdate password)
            throws CustomException, SQLException, ClassNotFoundException {
        int userId = TokenUtil.verifyTokenAndGetUserId(token);
        userService.setPassword(userId, password);
        return StatusMessage.successfulStatus();
    }
}
