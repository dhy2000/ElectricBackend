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
import java.util.List;

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
    @AuthRequired
    public StatusMessage addGame(
            @RequestHeader("Token") String token,
            @RequestBody GameInfoAdd game) throws SQLException, ClassNotFoundException, CustomException {
        TokenUtil.verifyTokenAndGetUserId(token);
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

    @ApiOperation("上线或下线某个游戏")
    @PostMapping("play")
    @AuthRequired
    public StatusMessage playGame(
            @RequestHeader("Token") String token,
            @RequestBody GameBehavior behavior) throws CustomException {
        int userId = TokenUtil.verifyTokenAndGetUserId(token);
        if (userId != behavior.getUserId()) {
            throw CustomException.defined(CustomException.Define.INVALID_SESSION);
        }
        if (behavior.getType() != 1 && behavior.getType() != 0) {
            throw CustomException.defined(CustomException.Define.ILLEGAL_BEHAVIOR);
        }
        gameService.changeGameOnline(behavior.getUserId(), behavior.getGameId(), behavior.getType() != 0);
        return StatusMessage.successfulStatus();
    }

    @ApiOperation("查询游戏登录状态, true 为在线, false 为离线")
    @GetMapping("status")
    public boolean gameStatus(
            @ApiParam("用户编号") @RequestParam("user") int userId,
            @ApiParam("游戏编号") @RequestParam("game") int gameId) {
        return gameService.isGameOnline(userId, gameId);
    }

    @ApiOperation("查询玩游戏的记录")
    @GetMapping("playRecord")
    public List<GamePlayRecord> playRecord(
            @ApiParam("用户编号") @RequestParam("user") int userId,
            @ApiParam("游戏编号") @RequestParam("game") int gameId) {
        return gameService.recordOnGame(userId, gameId);
    }

    @ApiOperation("查询用户玩游戏的总时长")
    @GetMapping("totalPlayTime")
    public int totalPlayTime(
            @ApiParam("用户编号") @RequestParam("user") int userId) {
        return gameService.totalGameTime(userId);
    }

    @ApiOperation("查询某用户已经获得的某游戏的所有成就")
    @GetMapping("acquirement")
    public Collection<GameAchievementAcquirement> acquirement(
            @ApiParam("用户 ID") @RequestParam("user") int userId,
            @ApiParam("游戏编号") @RequestParam("game") int gameId) {
        return gameService.achievementOfUserAndGame(gameId, userId);
    }

    @ApiOperation("获得游戏成就")
    @PostMapping("acquireAchievement")
    @AuthRequired
    public StatusMessage acquireAchievement(
            @RequestHeader("Token") String token,
            @RequestBody AcquireAchieveRequest request) throws CustomException {
        int userId = TokenUtil.verifyTokenAndGetUserId(token);
        int achieveId = request.getAchievementId();
        gameService.acquireAchievement(userId, achieveId);
        return StatusMessage.successfulStatus();
    }

    @ApiOperation("创建游戏成就")
    @PostMapping("createAchievement")
    @AuthRequired
    public StatusMessage createAchievement(
            @RequestHeader("Token") String token,
            @RequestBody AchievementCreate create) throws CustomException {
        TokenUtil.verifyTokenAndGetUserId(token);
        gameService.createAchievement(create.getGameId(), create.getName(), create.getDescribe());
        return StatusMessage.successfulStatus();
    }
}
