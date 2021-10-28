package com.dbproject.electricbackend.controller;

import com.dbproject.electricbackend.auth.AuthRequired;
import com.dbproject.electricbackend.auth.TokenUtil;
import com.dbproject.electricbackend.exception.CustomException;
import com.dbproject.electricbackend.schema.*;
import com.dbproject.electricbackend.service.GameService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
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

    @ApiOperation("获取游戏列表(本项目包含的游戏不会太多，不需要前端卷分页或懒加载等)")
    @GetMapping("list")
    public Collection<GameSummary> listGames()
            throws SQLException, ClassNotFoundException {
        return gameService.listGames();
    }

    @ApiOperation("获取某个游戏的详细信息")
    @GetMapping("info")
    public GameInfo gameInfo(@ApiParam("游戏编号") @RequestParam("game") int gameId)
            throws SQLException, CustomException, ClassNotFoundException {
        return gameService.getGameInfo(gameId);
    }

    @ApiOperation("获取某个游戏可达的全部成就")
    @GetMapping("achievement")
    public Collection<GameAchievement> achievements(
            @ApiParam("游戏编号") @RequestParam("game") int gameId)
            throws SQLException, ClassNotFoundException {
        return gameService.listAchievementsOfGame(gameId);
    }

    @ApiOperation("添加游戏")
    @PostMapping("addGame")
    public StatusMessage addGame(@RequestBody GameInfoAdd game) throws SQLException, ClassNotFoundException {
        gameService.addGame(game);
        return StatusMessage.successfulStatus();
    }

    @ApiOperation("购买游戏或创建订单：如 payNow 为 true 则立即付款(购买者需有足够余额)，否则仅创建未付款的订单")
    @PostMapping("purchaseGame")
    @AuthRequired
    public StatusMessage purchaseGame(
            @RequestHeader("Token") String token,
            @RequestBody GamePurchaseRequest request) throws CustomException {
        int userId = TokenUtil.verifyTokenAndGetUserId(token);
        if (!request.getBuyerId().equals(userId)) {
            throw CustomException.defined(CustomException.Define.INVALID_SESSION);
        }
        if (request.isPayNow()) {
            gameService.purchaseGame(request.getBuyerId(), request.getGameId(), request.getReceiverId());
        } else {
            gameService.addGameToCart(request.getBuyerId(), request.getGameId(), request.getReceiverId());
        }
        return StatusMessage.successfulStatus();
    }

    @ApiOperation("支付已有的订单")
    @PostMapping("payOrder")
    @AuthRequired
    public StatusMessage payOrder(
            @RequestHeader("Token") String token,
            @RequestBody PayOrderRequest request) throws CustomException {
        int userId = TokenUtil.verifyTokenAndGetUserId(token);
        if (!gameService.hasOrderOfBuyer(request.getOrderId(), userId)) {
            throw CustomException.defined(CustomException.Define.INVALID_SESSION);
        }
        gameService.payOrder(request.getOrderId());
        return StatusMessage.successfulStatus();
    }

}
