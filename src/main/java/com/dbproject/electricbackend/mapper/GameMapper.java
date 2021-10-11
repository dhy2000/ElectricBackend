package com.dbproject.electricbackend.mapper;

import com.dbproject.electricbackend.schema.GameAchievement;
import com.dbproject.electricbackend.schema.GameInfo;
import com.dbproject.electricbackend.schema.GameInfoAdd;
import com.dbproject.electricbackend.schema.GameSummary;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

//@Mapper
public interface GameMapper {
    Collection<GameSummary> listGames() throws SQLException, ClassNotFoundException;

    Optional<GameInfo> getGameInfo(int gameId) throws ClassNotFoundException, SQLException;

    Collection<GameAchievement> listAchievementsOfGame(int gameId) throws ClassNotFoundException, SQLException;

    void addGame(GameInfoAdd game) throws ClassNotFoundException, SQLException;
}
