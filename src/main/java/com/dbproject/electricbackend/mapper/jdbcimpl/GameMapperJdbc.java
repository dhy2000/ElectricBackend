package com.dbproject.electricbackend.mapper.jdbcimpl;

import com.dbproject.electricbackend.mapper.GameMapper;
import com.dbproject.electricbackend.schema.GameAchievement;
import com.dbproject.electricbackend.schema.GameInfo;
import com.dbproject.electricbackend.schema.GameSummary;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.*;
import java.util.*;

@Log4j2
@Component
public class GameMapperJdbc implements GameMapper {

    @Value("${spring.datasource.url}")
    private String dbAddress;

    @Value("${spring.datasource.driver-class-name}")
    private String dbDriverName;

    @Value("${spring.datasource.username}")
    private String dbUserName;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    private void close(Connection conn, PreparedStatement pst, ResultSet result) throws SQLException {
        if (Objects.nonNull(result)) {
            result.close();
        }
        if (Objects.nonNull(pst)) {
            pst.close();
        }
        if (Objects.nonNull(conn)) {
            conn.close();
        }
    }

    @Override
    public Collection<GameSummary> listGames() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement query = null;
        ResultSet result = null;
        List<GameSummary> gameList = new LinkedList<>();
        try {
            Class.forName(dbDriverName);
            conn = DriverManager.getConnection(dbAddress, dbUserName, dbPassword);
            String sql = "SELECT `id`, `name`, `price`, `is_multi_player` FROM `game`";
            query = conn.prepareStatement(sql);
            log.info(query);
            result = query.executeQuery();
            while (result.next()) {
                Integer id = result.getInt("id");
                String name = result.getString("name");
                Integer price = result.getInt("price");
                boolean multiPlayer = result.getBoolean("is_multi_player");
                gameList.add(new GameSummary(id, name, price, multiPlayer));
            }
            return gameList;
        } finally {
            close(conn, query, result);
        }
    }

    @Override
    public Optional<GameInfo> getGameInfo(int gameId) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement query = null;
        ResultSet result = null;
        try {
            Class.forName(dbDriverName);
            conn = DriverManager.getConnection(dbAddress, dbUserName, dbPassword);
            String sql = "SELECT `id`, `name`, `price`, `release_date`, `describe`, " +
                    "`support_device`, `is_multi_player`, `min_age` FROM `game` WHERE `id` = ?";
            query = conn.prepareStatement(sql);
            query.setInt(1, gameId);
            log.info(query);
            result = query.executeQuery();
            if (result.next()) {
                Integer id = result.getInt("id");
                String name = result.getString("name");
                Integer price = result.getInt("price");
                Date releaseDate = result.getDate("release_date");
                String describe = result.getString("describe");
                String supportDeviceStr = result.getString("support_device");
                boolean multiPlayer = result.getBoolean("is_multi_player");
                Integer minAge = result.getInt("min_age");
                GameInfo.SupportDevices supportDevices = parseSupportDevices(supportDeviceStr);
                return Optional.of(new GameInfo(id, name, price, releaseDate,
                        describe, supportDevices, multiPlayer, minAge));
            } else {
                return Optional.empty();
            }
        } finally {
            close(conn, query, result);
        }
    }

    private GameInfo.SupportDevices parseSupportDevices(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, GameInfo.SupportDevices.class);
    }

    @Override
    public Collection<GameAchievement> listAchievementsOfGame(int gameId)
            throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement query = null;
        ResultSet result = null;
        List<GameAchievement> achievementList = new LinkedList<>();
        try {
            Class.forName(dbDriverName);
            conn = DriverManager.getConnection(dbAddress, dbUserName, dbPassword);
            String sql = "SELECT `name`, `describe` FROM `achievement` WHERE `game_id` = ?";
            query = conn.prepareStatement(sql);
            query.setInt(1, gameId);
            log.info(query);
            result = query.executeQuery();
            while (result.next()) {
                String name = result.getString("name");
                String describe = result.getString("describe");
                achievementList.add(new GameAchievement(name, describe));
            }
            return achievementList;
        } finally {
            close(conn, query, result);
        }
    }
}
