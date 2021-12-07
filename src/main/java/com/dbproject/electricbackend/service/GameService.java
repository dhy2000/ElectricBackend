package com.dbproject.electricbackend.service;

import com.dbproject.electricbackend.exception.CustomException;
import com.dbproject.electricbackend.schema.*;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public interface GameService {

    Collection<GameSummary> listGames() throws SQLException, ClassNotFoundException;

    GameInfo getGameInfo(int gameId) throws SQLException, ClassNotFoundException, CustomException;

    Collection<GameAchievement> listAchievementsOfGame(int gameId) throws SQLException, ClassNotFoundException;

    void addGameToCart(int buyerId, int gameId, int receiverId);

    void purchaseGame(int buyerId, int gameId, int receiverId) throws CustomException;

    boolean hasOrderOfBuyer(int orderId, int buyerId);

    void payOrder(int orderId) throws CustomException;

    void addGame(GameInfoAdd game) throws SQLException, ClassNotFoundException;

    void changeGameOnline(int userId, int gameId, boolean direction) throws CustomException; // false: login, true: logout

    boolean isGameOnline(int userId, int gameId);

    List<GamePlayRecord> recordOnGame(int userId, int gameId);

    int totalGameTime(int userId);

    List<GameAchievementAcquirement> achievementOfUserAndGame(int gameId, int userId);

    void acquireAchievement(int userId, int achieveId) throws CustomException;

    void createAchievement(int gameId, String name, String description);
}
