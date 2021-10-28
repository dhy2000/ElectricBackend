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
    public void payOrder(int orderId) {
        Date now = new Date(new java.util.Date().getTime());
        userGameMapper.payOrder(orderId, now);
        PurchaseGameOrder order = userGameMapper.getOrder(orderId);
        userGameMapper.consumeGame(order.getBuyerId(), order.getGameId());
    }

    @Override
    public void addGame(GameInfoAdd game) throws SQLException, ClassNotFoundException {
        gameMapper.addGame(game);
    }
}
