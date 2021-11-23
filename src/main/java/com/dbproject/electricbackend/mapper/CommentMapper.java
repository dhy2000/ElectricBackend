package com.dbproject.electricbackend.mapper;

import com.dbproject.electricbackend.schema.Comment;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface CommentMapper {
    @Insert("INSERT INTO comment (author_id, title, content, create_time, reply_to, game_id) VALUES (#{author_id}, #{title}, #{content}, #{time}, #{reply}, #{game_id})")
    void createComment(
            @Param("game_id") int gameId,
            @Param("author_id") int authorId,
            @Param("title") String title,
            @Param("content") String content,
            @Param("time") Timestamp time,
            @Param("reply") Integer reply);

    @Update("UPDATE comment SET visible = FALSE WHERE id = #{comment_id} AND author_id = #{author_id}")
    void deleteComment(@Param("comment_id") int commentId, @Param("author_id") int userId); // lazy delete

    @Select("SELECT comment.id, author_id, u.nickname author_nickname, title, content, create_time, reply_to FROM comment INNER JOIN user u on comment.author_id = u.id WHERE game_id = #{game_id} AND visible = TRUE")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "authorId", column = "author_id"),
            @Result(property = "authorNickname", column = "author_nickname"),
            @Result(property = "title", column = "title"),
            @Result(property = "content", column = "content"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "replyId", column = "reply_to")
    })
    List<Comment> listCommentsOfGame(@Param("game_id") int gameId);
}
