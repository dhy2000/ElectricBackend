package com.dbproject.electricbackend.controller;

import com.dbproject.electricbackend.auth.AuthRequired;
import com.dbproject.electricbackend.auth.TokenUtil;
import com.dbproject.electricbackend.exception.CustomException;
import com.dbproject.electricbackend.schema.GameMod;
import com.dbproject.electricbackend.schema.GameModCreate;
import com.dbproject.electricbackend.schema.SingleIdRequest;
import com.dbproject.electricbackend.schema.StatusMessage;
import com.dbproject.electricbackend.service.ModService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "扩展包管理")
@RequestMapping("/mod")
@RestController
public class ModController {
    private final ModService modService;

    @Autowired
    public ModController(ModService modService) {
        this.modService = modService;
    }

    @ApiOperation("创建MOD")
    @PostMapping("create")
    @AuthRequired
    public StatusMessage createMod(
            @RequestHeader("Token") String token,
            @RequestBody GameModCreate create) throws CustomException {
        int userId = TokenUtil.verifyTokenAndGetUserId(token);
        modService.createMod(userId, create);
        return StatusMessage.successfulStatus();
    }

    @ApiOperation("更新MOD")
    @PostMapping("update")
    @AuthRequired
    public StatusMessage updateMod(
            @RequestHeader("Token") String token,
            @RequestBody SingleIdRequest request) throws CustomException {
        int userId = TokenUtil.verifyTokenAndGetUserId(token);
        modService.updateMod(userId, request.getId());
        return StatusMessage.successfulStatus();
    }

    @ApiOperation("查询游戏的 MOD 列表")
    @GetMapping("list")
    public List<GameMod> listMod(
            @ApiParam("游戏ID") @RequestParam("game") int game) {
        return modService.modsOfGame(game);
    }

    @ApiOperation("订阅 MOD, 参数为 MOD 编号")
    @PostMapping("subscribe")
    @AuthRequired
    public StatusMessage subscribeMod(
            @RequestHeader("Token") String token,
            @RequestBody SingleIdRequest request) throws CustomException {
        int userId = TokenUtil.verifyTokenAndGetUserId(token);
        modService.subscribe(userId, request.getId());
        return StatusMessage.successfulStatus();
    }

    @ApiOperation("取消订阅 MOD, 参数为 MOD 编号")
    @PostMapping("unsubscribe")
    @AuthRequired
    public StatusMessage unsubscribeMod(
            @RequestHeader("Token") String token,
            @RequestBody SingleIdRequest request) throws CustomException {
        int userId = TokenUtil.verifyTokenAndGetUserId(token);
        modService.unsubscribe(userId, request.getId());
        return StatusMessage.successfulStatus();
    }

    @ApiOperation("查询用户订阅的 MOD 列表")
    @GetMapping("subscriptions")
    public List<GameMod> modSubscriptions(
            @ApiParam("用户ID") @RequestParam("user") int user) {
        return modService.subscribedMods(user);
    }
}
