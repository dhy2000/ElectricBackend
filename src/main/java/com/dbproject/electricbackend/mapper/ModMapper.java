package com.dbproject.electricbackend.mapper;

import com.dbproject.electricbackend.schema.GameMod;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface ModMapper {

    @Insert("INSERT INTO game_mod (name, `describe`, author_id, create_time, update_time, game_id) VALUES (#{name}, #{describe}, #{author_id}, #{time}, #{time}, #{game_id})")
    void createMod(
            @Param("name") String name,
            @Param("describe") String describe,
            @Param("author_id") int authorId,
            @Param("time") Timestamp time,
            @Param("game_id") int gameId);

    @Update("UPDATE game_mod SET update_time = #{time} WHERE author_id = #{user_id} AND id = #{mod_id}")
    void updateMod(@Param("user_id") int userId, @Param("mod_id") int modId, @Param("time") Timestamp time);

    @Insert("INSERT INTO mod_subscription (user_id, mod_id) VALUES (#{user_id}, #{mod_id})")
    void subscribe(@Param("user_id") int userId, @Param("mod_id") int modId);

    @Delete("DELETE FROM mod_subscription WHERE user_id = #{user_id} AND mod_id = #{mod_id}")
    void unsubscribe(@Param("user_id") int userId, @Param("mod_id") int modId);

    @Select("SELECT EXISTS(SELECT * FROM mod_subscription WHERE user_id = #{user_id} AND mod_id = #{mod_id})")
    boolean isSubscribed(@Param("user_id") int userId, @Param("mod_id") int modId);

    @Select("SELECT game_mod.id, game_mod.name, game_mod.`describe`, game_mod.author_id, u.nickname, create_time, update_time, game_id, g.name game_name FROM game_mod\n" +
            "    INNER JOIN game g on game_mod.game_id = g.id\n" +
            "    INNER JOIN user u on game_mod.author_id = u.id\n" +
            "WHERE game_id = #{game_id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "describe", column = "describe"),
            @Result(property = "authorId", column = "author_id"),
            @Result(property = "authorNickname", column = "nickname"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time")
    })
    List<GameMod> modsOfGame(@Param("game_id") int gameId);

    @Select("SELECT game_mod.id, game_mod.name, game_mod.`describe`, game_mod.author_id, u.nickname, create_time, update_time, game_id, g.name game_name FROM game_mod\n" +
            "    INNER JOIN game g on game_mod.game_id = g.id\n" +
            "    INNER JOIN user u on game_mod.author_id = u.id\n" +
            "    INNER JOIN mod_subscription ms on game_mod.id = ms.mod_id\n" +
            "WHERE ms.user_id = #{user_id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "describe", column = "describe"),
            @Result(property = "authorId", column = "author_id"),
            @Result(property = "authorNickname", column = "nickname"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time")
    })
    List<GameMod> subscribedMods(@Param("user_id") int userId);
}
