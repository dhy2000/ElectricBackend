package com.dbproject.electricbackend.service;

import com.dbproject.electricbackend.schema.GameAchievement;
import com.dbproject.electricbackend.schema.GameInfo;
import com.dbproject.electricbackend.schema.GameSummary;

import java.util.Collection;
import java.util.Optional;

public interface GameService {

    Collection<GameSummary> listGames();

    GameInfo getGameInfo(int gameId);

    Collection<GameAchievement> listAchievementsOfGame(int gameId);

    void addGameToCart(int userId, int gameId);

    void purchaseGame(int userId, int gameId);


}
