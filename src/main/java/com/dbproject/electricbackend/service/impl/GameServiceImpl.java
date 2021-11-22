package com.dbproject.electricbackend.service.impl;

import com.dbproject.electricbackend.exception.CustomException;
import com.dbproject.electricbackend.mapper.GameMapper;
import com.dbproject.electricbackend.mapper.UserGameMapper;
import com.dbproject.electricbackend.schema.*;
import com.dbproject.electricbackend.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {

    private final GameMapper gameMapper;
    private final UserGameMapper userGameMapper;

    @Autowired
    public GameServiceImpl(GameMapper gameMapper, UserGameMapper userGameMapper) {
        this.gameMapper = gameMapper;
        this.userGameMapper = userGameMapper;
    }

    @Override
    public Collection<GameSummary> listGames() throws SQLException, ClassNotFoundException {
        return gameMapper.listGames();
    }

    @Override
    public GameInfo getGameInfo(int gameId) throws SQLException, ClassNotFoundException, CustomException {
        Optional<GameInfo> game = gameMapper.getGameInfo(gameId);
        if (!game.isPresent()) {
            throw CustomException.defined(CustomException.Define.NON_EXIST_GAME);
        }
        return game.get();
    }

    @Override
    public Collection<GameAchievement> listAchievementsOfGame(int gameId) throws SQLException, ClassNotFoundException {
        return gameMapper.listAchievementsOfGame(gameId);
    }

    @Override
    public void addGameToCart(int buyerId, int gameId, int receiverId) {
        if (!userGameMapper.hasOrder(buyerId, gameId, receiverId)) {
            Date now = new Date(new java.util.Date().getTime());
            userGameMapper.createUnpaidOrder(buyerId, receiverId, gameId, now);
        }
    }

    @Override
    public void purchaseGame(int buyerId, int gameId, int receiverId) throws CustomException {
        boolean canPurchase = userGameMapper.hasEnoughMoneyPurchaseGame(buyerId, gameId);
        if (!canPurchase) {
            throw CustomException.defined(CustomException.Define.NOT_ENOUGH_BALANCE);
        }
        Date now = new Date(new java.util.Date().getTime());
        if (userGameMapper.hasOrder(buyerId, gameId, receiverId)) {
            int orderId = userGameMapper.getOrderId(buyerId, gameId, receiverId);
            userGameMapper.payOrder(orderId, now);
        } else {
            userGameMapper.createPaidOrder(buyerId, receiverId, gameId, now, now);
            userGameMapper.consumeGame(buyerId, gameId);
        }
    }

    @Override
    public boolean hasOrderOfBuyer(int orderId, int buyerId) {
        return userGameMapper.hasOrderWithBuyer(orderId, buyerId);
    }

    @Override
    public void payOrder(int orderId) throws CustomException {
        PurchaseGameOrder order = userGameMapper.getOrder(orderId);
        if (Objects.isNull(order)) {
            throw CustomException.defined(CustomException.Define.INVALID_ORDER);
        }
        boolean canPurchase = userGameMapper.hasEnoughMoneyPurchaseGame(order.getBuyerId(), order.getGameId());
        if (!canPurchase) {
            throw CustomException.defined(CustomException.Define.NOT_ENOUGH_BALANCE);
        }
        Date now = new Date(new java.util.Date().getTime());
        userGameMapper.payOrder(orderId, now);
        userGameMapper.consumeGame(order.getBuyerId(), order.getGameId());
    }

    @Override
    public void addGame(GameInfoAdd game) throws SQLException, ClassNotFoundException {
        gameMapper.addGame(game);
    }

    @Override
    public void changeGameOnline(int userId, int gameId, boolean direction) throws CustomException {
        // false: online, true: offline
        if (direction) {
            if (isGameOnline(userId, gameId)) {
                Date now = new Date(new java.util.Date().getTime());
                userGameMapper.logoutGame(userId, gameId, now);
            } else {
                throw CustomException.defined(CustomException.Define.ALREADY_OFFLINE);
            }
        } else {
            if (!isGameOnline(userId, gameId)) {
                Date now = new Date(new java.util.Date().getTime());
                userGameMapper.loginGame(userId, gameId, now);
            } else {
                throw CustomException.defined(CustomException.Define.ALREADY_ONLINE);
            }
        }
    }

    @Override
    public boolean isGameOnline(int userId, int gameId) {
        return userGameMapper.isGameOnline(userId, gameId);
    }

    @Override
    public List<GamePlayRecord> recordOnGame(int userId, int gameId) {
        return userGameMapper.getPlayRecordsOnGame(userId, gameId);
    }

    @Override
    public int totalGameTime(int userId) {
        return userGameMapper.getTotalGameDuration(userId);
    }

    @Override
    public List<GameAchievementAcquirement> achievementOfUserAndGame(int gameId, int userId) {
        return userGameMapper.achievementOfUserAndGame(gameId, userId);
    }

    @Override
    public void acquireAchievement(int userId, int achieveId) throws CustomException {
        if (userGameMapper.hasAchievement(userId, achieveId)) {
            throw CustomException.defined(CustomException.Define.ACQUIRED_ACHIEVEMENT);
        }
        Date now = new Date(new java.util.Date().getTime());
        userGameMapper.acquireAchievement(userId, achieveId, now);
    }
}
