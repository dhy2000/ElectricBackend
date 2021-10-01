package com.dbproject.electricbackend.controller;

import com.dbproject.electricbackend.schema.GameInfo;
import com.dbproject.electricbackend.schema.GameSummary;
import com.dbproject.electricbackend.service.GameService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@Api(tags = "游戏")
@RequestMapping("/game")
@RestController
public class GameController {
    private final GameService gameService;

    @Autowired
    public GameController(GameService service) {
        this.gameService = service;
    }

    @ApiOperation("TODO 获取游戏列表(本项目包含的游戏不会太多，不需要前端卷分页或懒加载等)")
    @GetMapping("list")
    public Collection<GameSummary> listGames() {
        return gameService.listGames();
    }

    @ApiOperation("TODO 获取某个游戏的详细信息")
    @GetMapping("info")
    public GameInfo gameInfo(@ApiParam("游戏编号") @RequestParam("game") int gameId) {
        return gameService.getGameInfo(gameId);
    }

}
