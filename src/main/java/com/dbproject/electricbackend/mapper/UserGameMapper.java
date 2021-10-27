package com.dbproject.electricbackend.mapper;

import com.dbproject.electricbackend.schema.GameAchievement;
import com.dbproject.electricbackend.schema.GameOfUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserGameMapper {

    @Select("SELECT DISTINCT `game`.`id` AS game_id, `game`.`name`, user.id AS user_id FROM `order` INNER JOIN game ON `order`.game_id = game.id INNER JOIN user ON `order`.receiver_id = user.id WHERE `order`.receiver_id = #{userId} AND `order`.is_paid = TRUE")
    @Results({
            @Result(property = "id", column = "game_id"),
            @Result(property = "name", column = "name"),
            @Result(
                    property = "achievements", javaType = List.class,
                    column = "{gameId=game_id,userId=user_id}",
                    many = @Many(select = "achievementOfUserAndGame")
            )
    })
    List<GameOfUser> gameOfUser(@Param("userId") int userId);

    @Select("SELECT achievement.`achieve_id`, achievement.`name`, achievement.`describe` FROM achievement INNER JOIN game ON achievement.game_id = game.id INNER JOIN achieve_acquirement ON achievement.achieve_id = achieve_acquirement.achieve_id WHERE achievement.game_id = #{gameId} AND achieve_acquirement.user_id = #{userId}")
    @Results({
            @Result(property = "id", column = "achieve_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "describe", column = "describe")
    })
    List<GameAchievement> achievementOfUserAndGame(@Param("gameId") int gameId, @Param("userId") int userId);
}
