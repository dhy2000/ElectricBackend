package com.dbproject.electricbackend.service;

import com.dbproject.electricbackend.exception.CustomException;
import com.dbproject.electricbackend.schema.GameAchievement;
import com.dbproject.electricbackend.schema.GameInfo;
import com.dbproject.electricbackend.schema.GameSummary;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

public interface GameService {

    Collection<GameSummary> listGames() throws SQLException, ClassNotFoundException;

    GameInfo getGameInfo(int gameId) throws SQLException, ClassNotFoundException, CustomException;

    Collection<GameAchievement> listAchievementsOfGame(int gameId) throws SQLException, ClassNotFoundException;

    void addGameToCart(int userId, int gameId);

    void purchaseGame(int userId, int gameId);


}
