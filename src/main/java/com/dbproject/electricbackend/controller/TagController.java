package com.dbproject.electricbackend.controller;

import com.dbproject.electricbackend.auth.AuthRequired;
import com.dbproject.electricbackend.auth.TokenUtil;
import com.dbproject.electricbackend.exception.CustomException;
import com.dbproject.electricbackend.schema.*;
import com.dbproject.electricbackend.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "标签管理")
@RequestMapping("/tag")
@RestController
public class TagController {
    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @ApiOperation("列出所有的标签")
    @GetMapping("list")
    public List<Tag> listTags() {
        return tagService.listAllTags();
    }

    @ApiOperation("创建标签")
    @PostMapping("create")
    @AuthRequired
    public StatusMessage createTag(
            @RequestHeader("Token") String token,
            @RequestBody SingleNameRequest request) throws CustomException {
        TokenUtil.verifyTokenAndGetUserId(token);
        tagService.createTag(request.getName());
        return StatusMessage.successfulStatus();
    }

    @ApiOperation("删除标签")
    @PostMapping("delete")
    @AuthRequired
    public StatusMessage deleteTag(
            @RequestHeader("Token") String token,
            @RequestBody SingleIdRequest request) throws CustomException {
        TokenUtil.verifyTokenAndGetUserId(token);
        tagService.deleteTag(request.getId());
        return StatusMessage.successfulStatus();
    }

    @ApiOperation("列出某个游戏的标签")
    @GetMapping("of")
    public List<Tag> tagsOfGame(@ApiParam("游戏ID") @RequestParam("game") int game) {
        return tagService.tagsOfGame(game);
    }

    @ApiOperation("游戏标签增删")
    @PostMapping("manage")
    @AuthRequired
    public StatusMessage manageTag(
            @RequestHeader("Token") String token,
            @RequestBody TagRequest request) throws CustomException {
        TokenUtil.verifyTokenAndGetUserId(token);
        int type = request.getType();
        if (type != 0 && type != 1) {
            throw CustomException.defined(CustomException.Define.ILLEGAL_BEHAVIOR);
        }
        int game = request.getGameId();
        int tag = request.getTagId();
        if (type == 0) {
            tagService.addTag(game, tag);
        } else {
            tagService.removeTag(game, tag);
        }
        return StatusMessage.successfulStatus();
    }
}
