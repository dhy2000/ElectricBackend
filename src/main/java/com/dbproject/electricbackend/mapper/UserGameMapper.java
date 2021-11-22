package com.dbproject.electricbackend.mapper;

import com.dbproject.electricbackend.schema.*;
import org.apache.ibatis.annotations.*;

import java.sql.Date;
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

    @Select("SELECT game_id, game.name game_name, achievement.`achieve_id`, achievement.`name` achieve_name, achievement.`describe`, acquire_time FROM achievement INNER JOIN game ON achievement.game_id = game.id INNER JOIN achieve_acquirement ON achievement.achieve_id = achieve_acquirement.achieve_id WHERE achievement.game_id = #{gameId} AND achieve_acquirement.user_id = #{userId}")
    @Results({
            @Result(property = "gameId", column = "game_id"),
            @Result(property = "gameName", column = "game_name"),
            @Result(property = "achievementId", column = "achieve_id"),
            @Result(property = "achievementName", column = "achieve_name"),
            @Result(property = "describe", column = "describe"),
            @Result(property = "acquireTime", column = "acquire_time")
    })
    List<GameAchievementAcquirement> achievementOfUserAndGame(@Param("gameId") int gameId, @Param("userId") int userId);

    @Select("SELECT EXISTS(SELECT * FROM achieve_acquirement WHERE user_id = #{user_id} AND achieve_id = #{achieve_id})")
    boolean hasAchievement(@Param("user_id") int userId, @Param("achieve_id") int achievementId);

    @Insert("INSERT INTO achieve_acquirement (user_id, achieve_id, acquire_time) VALUES (#{user_id}, #{achieve_id}, #{time})")
    void acquireAchievement(@Param("user_id") int userId, @Param("achieve_id") int achievementId, @Param("time") Date time);

    @Select("SELECT * FROM `order` WHERE buyer_id = #{buyer_id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "buyerId", column = "buyer_id"),
            @Result(property = "gameId", column = "game_id"),
            @Result(property = "receiverId", column = "receiver_id"),
            @Result(property = "createDate", column = "create_date"),
            @Result(property = "paid", column = "is_paid"),
            @Result(property = "payDate", column = "pay_date")
    })
    List<PurchaseGameOrder> getOrdersOfBuyer(@Param("buyer_id") int buyerId);

    @Select("SELECT * FROM `order` WHERE id = #{order_id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "buyerId", column = "buyer_id"),
            @Result(property = "gameId", column = "game_id"),
            @Result(property = "receiverId", column = "receiver_id"),
            @Result(property = "createDate", column = "create_date"),
            @Result(property = "paid", column = "is_paid"),
            @Result(property = "payDate", column = "pay_date")
    })
    PurchaseGameOrder getOrder(@Param("order_id") int orderId);

    @Select("SELECT (SELECT balance FROM user WHERE user.id = #{user_id}) >= (SELECT price FROM game WHERE game.id = #{game_id})")
    boolean hasEnoughMoneyPurchaseGame(@Param("user_id") int userId, @Param("game_id") int gameId);

    @Insert("INSERT INTO `order` (buyer_id, game_id, receiver_id, create_date, is_paid, pay_date) VALUES (#{buyer_id}, #{game_id}, #{receiver_id}, #{create_date}, 0, NULL)")
    void createUnpaidOrder(@Param("buyer_id") int buyerId, @Param("receiver_id") int receiverId, @Param("game_id") int gameId, @Param("create_date") Date createDate);

    @Insert("INSERT INTO `order` (buyer_id, game_id, receiver_id, create_date, is_paid, pay_date) VALUES (#{buyer_id}, #{game_id}, #{receiver_id}, #{create_date}, 1, #{pay_date})")
    void createPaidOrder(@Param("buyer_id") int buyerId, @Param("receiver_id") int receiverId, @Param("game_id") int gameId, @Param("create_date") Date createDate, @Param("pay_date") Date payDate);

    @Update("UPDATE `order` SET is_paid = 1, pay_date = #{pay_date} WHERE id = #{order_id} AND is_paid = 0")
    void payOrder(@Param("order_id") int orderId, @Param("pay_date") Date payDate);

    @Update("UPDATE user SET balance = balance - (SELECT price FROM game WHERE game.id = #{game_id}) WHERE id = #{buyer_id} AND balance >= (SELECT price FROM game WHERE game.id = #{game_id})")
    void consumeGame(@Param("buyer_id") int buyerId, @Param("game_id") int gameId);

    @Select("SELECT EXISTS(SELECT * FROM `order` WHERE buyer_id = #{buyer_id} AND game_id = #{game_id} AND receiver_id = #{receiver_id})")
    boolean hasOrder(@Param("buyer_id") int buyerId, @Param("game_id") int gameId, @Param("receiver_id") int receiverId);

    @Select("SELECT id FROM `order` WHERE buyer_id = #{buyer_id} AND game_id = #{game_id} AND receiver_id = #{receiver_id}")
    int getOrderId(@Param("buyer_id") int buyerId, @Param("game_id") int gameId, @Param("receiver_id") int receiverId);

    @Select("SELECT EXISTS(SELECT * FROM `order` WHERE id=#{order_id} AND buyer_id=#{buyer_id})")
    boolean hasOrderWithBuyer(@Param("order_id") int orderId, @Param("buyer_id") int buyerId);

    @Insert("INSERT INTO play_record (user_id, game_id, start_time, end_time) VALUES (#{user_id}, #{game_id}, #{start_time}, null)")
    void loginGame(@Param("user_id") int userId, @Param("game_id") int gameId, @Param("start_time") Date startTime);

    @Update("UPDATE play_record SET end_time=#{end_time} WHERE user_id = #{user_id} AND game_id = #{game_id} AND end_time IS NULL")
    void logoutGame(@Param("user_id") int userId, @Param("game_id") int gameId, @Param("end_time") Date endTime);

    @Select("SELECT EXISTS(SELECT * FROM play_record WHERE user_id = #{user_id} AND game_id = #{game_id} AND end_time IS NULL)")
    boolean isGameOnline(@Param("user_id") int userId, @Param("game_id") int gameId);

    @Select("SELECT game_id, game.name game_name, start_time, end_time, (UNIX_TIMESTAMP(end_time) - UNIX_TIMESTAMP(start_time)) duration FROM play_record INNER JOIN game ON play_record.game_id = game.id WHERE user_id = #{user_id} AND game_id = #{game_id}")
    @Results({
            @Result(property = "gameId", column = "game_id"),
            @Result(property = "gameName", column = "game_name"),
            @Result(property = "startTime", column = "start_time"),
            @Result(property = "endTime", column = "end_time"),
            @Result(property = "duration", column = "duration")
    })
    List<GamePlayRecord> getPlayRecordsOnGame(@Param("user_id") int userId, @Param("game_id") int gameId);

    @Select("SELECT COALESCE(SUM(UNIX_TIMESTAMP(end_time) - UNIX_TIMESTAMP(start_time)), 0) FROM play_record WHERE user_id = #{user_id}")
    int getTotalGameDuration(@Param("user_id") int userId);
}
