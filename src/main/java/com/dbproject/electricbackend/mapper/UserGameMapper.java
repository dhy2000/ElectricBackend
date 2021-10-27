package com.dbproject.electricbackend.mapper;

import com.dbproject.electricbackend.schema.GameAchievement;
import com.dbproject.electricbackend.schema.GameOfUser;
import com.dbproject.electricbackend.schema.PurchaseGameOrder;
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

    @Select("SELECT achievement.`achieve_id`, achievement.`name`, achievement.`describe` FROM achievement INNER JOIN game ON achievement.game_id = game.id INNER JOIN achieve_acquirement ON achievement.achieve_id = achieve_acquirement.achieve_id WHERE achievement.game_id = #{gameId} AND achieve_acquirement.user_id = #{userId}")
    @Results({
            @Result(property = "id", column = "achieve_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "describe", column = "describe")
    })
    List<GameAchievement> achievementOfUserAndGame(@Param("gameId") int gameId, @Param("userId") int userId);

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

    @Update("UPDATE `order` SET is_paid = 1, pay_date = #{pay_date} WHERE id = #{order_id}")
    void payOrder(@Param("order_id") int orderId, @Param("pay_date") Date payDate);

    @Update("UPDATE user SET balance = balance - (SELECT price FROM game WHERE game.id = #{game_id}) WHERE id = #{buyer_id} AND balance >= (SELECT price FROM game WHERE game.id = #{game_id})")
    void consumeGame(@Param("buyer_id") int buyerId, @Param("game_id") int gameId);

    @Select("SELECT EXISTS(SELECT * FROM `order` WHERE buyer_id = #{buyer_id} AND game_id = #{game_id} AND receiver_id = #{receiver_id})")
    boolean hasOrder(@Param("buyer_id") int buyerId, @Param("game_id") int gameId, @Param("receiver_id") int receiverId);

    @Select("SELECT id FROM `order` WHERE buyer_id = #{buyer_id} AND game_id = #{game_id} AND receiver_id = #{receiver_id}")
    int getOrderId(@Param("buyer_id") int buyerId, @Param("game_id") int gameId, @Param("receiver_id") int receiverId);

    @Select("SELECT EXISTS(SELECT * FROM `order` WHERE id=#{order_id} AND buyer_id=#{buyer_id})")
    boolean hasOrderWithBuyer(@Param("order_id") int orderId, @Param("buyer_id") int buyerId);
}
