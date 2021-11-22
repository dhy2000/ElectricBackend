package com.dbproject.electricbackend.mapper;

import com.dbproject.electricbackend.schema.UserSummary;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FriendMapper {

    @Select("SELECT EXISTS(SELECT * FROM friendship WHERE (user_id1 = #{user_id1} AND user_id2 = #{user_id2}) OR (user_id1 = #{user_id2} AND user_id2 = #{user_id1}))\n")
    boolean isFriend(@Param("user_id1") int userId1, @Param("user_id2") int userId2);

    @Insert("INSERT INTO friendship (user_id1, user_id2) VALUES (#{user_id1}, #{user_id2})")
    void addFriend(@Param("user_id1") int userId1, @Param("user_id2") int userId2);

    @Delete("DELETE FROM friendship WHERE (user_id1 = #{user_id1} AND user_id2 = #{user_id2}) OR (user_id1 = #{user_id2} AND user_id2 = #{user_id1})")
    void delFriend(@Param("user_id1") int userId1, @Param("user_id2") int userId2);

    @Select("SELECT user_id2 user_id FROM friendship WHERE user_id1 = #{user_id}\n" +
            "UNION\n" +
            "SELECT user_id1 user_id FROM friendship WHERE user_id2 = #{user_id}\n" +
            "ORDER BY user_id")
    List<Integer> listFriendsById(@Param("user_id") int userId);

    @Select("SELECT `user`.id, `user`.username, `user`.nickname FROM (\n" +
            "    SELECT user_id2 user_id FROM friendship WHERE user_id1 = #{user_id}\n" +
            "    UNION\n" +
            "    SELECT user_id1 user_id FROM friendship WHERE user_id2 = #{user_id}\n" +
            ") friend_list INNER JOIN `user` ON `user`.id = friend_list.user_id ORDER BY `user`.id\n")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "nickname", column = "nickname")
    })
    List<UserSummary> listFriends(@Param("user_id") int userId);
}
