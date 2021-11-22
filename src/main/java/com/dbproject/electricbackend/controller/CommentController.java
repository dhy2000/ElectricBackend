package com.dbproject.electricbackend.controller;

import com.dbproject.electricbackend.auth.AuthRequired;
import com.dbproject.electricbackend.auth.TokenUtil;
import com.dbproject.electricbackend.exception.CustomException;
import com.dbproject.electricbackend.schema.Comment;
import com.dbproject.electricbackend.schema.CommentCreate;
import com.dbproject.electricbackend.schema.SingleIdRequest;
import com.dbproject.electricbackend.schema.StatusMessage;
import com.dbproject.electricbackend.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "评论管理")
@RequestMapping("/comment")
@RestController
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @ApiOperation("发布评论")
    @PostMapping("create")
    @AuthRequired
    public StatusMessage createComment(
            @RequestHeader("Token") String token,
            @RequestBody CommentCreate create) throws CustomException {
        int userId = TokenUtil.verifyTokenAndGetUserId(token);
        commentService.createComment(userId, create);
        return StatusMessage.successfulStatus();
    }

    @ApiOperation("删除评论")
    @PostMapping("delete")
    @AuthRequired
    public StatusMessage deleteComment(
            @RequestHeader("Token") String token,
            @RequestBody SingleIdRequest request) throws CustomException {
        int userId = TokenUtil.verifyTokenAndGetUserId(token);
        commentService.deleteComment(userId, request.getId());
        return StatusMessage.successfulStatus();
    }

    @ApiOperation("列出属于游戏的所有评论")
    @GetMapping("list")
    public List<Comment> listComment(@ApiParam("游戏ID") @RequestParam("game") int gameId) {
        return commentService.listCommentOfGame(gameId);
    }
}
