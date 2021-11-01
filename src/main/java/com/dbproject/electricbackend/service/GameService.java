package com.dbproject.electricbackend.service;

import com.dbproject.electricbackend.exception.CustomException;
import com.dbproject.electricbackend.schema.GameAchievement;
import com.dbproject.electricbackend.schema.GameInfo;
import com.dbproject.electricbackend.schema.GameInfoAdd;
import com.dbproject.electricbackend.schema.GameSummary;

import java.sql.SQLException;
import java.util.Collection;

public interface GameService {

    Collection<GameSummary> listGames() throws SQLException, ClassNotFoundException;

    GameInfo getGameInfo(int gameId) throws SQLException, ClassNotFoundException, CustomException;

    Collection<GameAchievement> listAchievementsOfGame(int gameId) throws SQLException, ClassNotFoundException;

    void addGameToCart(int buyerId, int gameId, int receiverId);

    void purchaseGame(int buyerId, int gameId, int receiverId) throws CustomException;

    boolean hasOrderOfBuyer(int orderId, int buyerId);

    void payOrder(int orderId) throws CustomException;

    void addGame(GameInfoAdd game) throws SQLException, ClassNotFoundException;
}
