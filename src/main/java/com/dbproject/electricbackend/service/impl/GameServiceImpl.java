package com.dbproject.electricbackend.service.impl;

import com.dbproject.electricbackend.schema.GameAchievement;
import com.dbproject.electricbackend.schema.GameInfo;
import com.dbproject.electricbackend.schema.GameSummary;
import com.dbproject.electricbackend.service.GameService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {
    @Override
    public Collection<GameSummary> listGames() {
        return null;
    }

    @Override
    public GameInfo getGameInfo(int gameId) {
        return null;
    }

    @Override
    public Collection<GameAchievement> listAchievementsOfGame(int gameId) {
        return null;
    }

    @Override
    public void addGameToCart(int userId, int gameId) {

    }

    @Override
    public void purchaseGame(int userId, int gameId) {

    }
}
