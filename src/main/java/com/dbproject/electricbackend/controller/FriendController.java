package com.dbproject.electricbackend.controller;

import com.dbproject.electricbackend.auth.AuthRequired;
import com.dbproject.electricbackend.auth.TokenUtil;
import com.dbproject.electricbackend.exception.CustomException;
import com.dbproject.electricbackend.schema.FriendRequest;
import com.dbproject.electricbackend.schema.StatusMessage;
import com.dbproject.electricbackend.schema.UserSummary;
import com.dbproject.electricbackend.service.FriendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Api(tags = "好友关系")
@RequestMapping("/friend")
@RestController
public class FriendController {
    private final FriendService friendService;

    @Autowired
    public FriendController(FriendService friendService) {
        this.friendService = friendService;
    }

    @ApiOperation("获取用户的好友列表")
    @GetMapping("list")
    public List<UserSummary> friendsList(@ApiParam("用户 ID") @RequestParam("user") int userId) {
        return friendService.friendListById(userId);
    }

    @ApiOperation("添加好友")
    @PostMapping("add")
    @AuthRequired
    public StatusMessage addFriend(
            @RequestHeader("Token") String token,
            @RequestBody FriendRequest request)
            throws CustomException, SQLException, ClassNotFoundException {
        final int userId = TokenUtil.verifyTokenAndGetUserId(token);
        final String otherName = request.getUsername();
        friendService.addFriendByIdAndName(userId, otherName);
        return StatusMessage.successfulStatus();
    }

    @ApiOperation("删除好友")
    @PostMapping("delete")
    @AuthRequired
    public StatusMessage delFriend(
            @RequestHeader("Token") String token,
            @RequestBody FriendRequest request)
            throws CustomException, SQLException, ClassNotFoundException {
        final int userId = TokenUtil.verifyTokenAndGetUserId(token);
        final String otherName = request.getUsername();
        friendService.delFriendByIdAndName(userId, otherName);
        return StatusMessage.successfulStatus();
    }

}
