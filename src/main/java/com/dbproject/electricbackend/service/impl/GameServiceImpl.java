package com.dbproject.electricbackend.service.impl;

import com.dbproject.electricbackend.exception.CustomException;
import com.dbproject.electricbackend.mapper.GameMapper;
import com.dbproject.electricbackend.schema.GameAchievement;
import com.dbproject.electricbackend.schema.GameInfo;
import com.dbproject.electricbackend.schema.GameSummary;
import com.dbproject.electricbackend.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {

    private final GameMapper gameMapper;

    @Autowired
    public GameServiceImpl(GameMapper gameMapper) {
        this.gameMapper = gameMapper;
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
    public void addGameToCart(int userId, int gameId) {

    }

    @Override
    public void purchaseGame(int userId, int gameId) {

    }
}
