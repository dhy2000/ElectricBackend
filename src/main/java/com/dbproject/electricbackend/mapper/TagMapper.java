package com.dbproject.electricbackend.mapper;

import com.dbproject.electricbackend.schema.Tag;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TagMapper {

    @Insert("INSERT INTO tag (name) VALUES (#{name})")
    void createTag(@Param("name") String name);

    @Delete("DELETE FROM tag WHERE id = #{tag_id}")
    void deleteTag(@Param("tag_id") int tagId);

    @Select("SELECT id, name FROM tag")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name")
    })
    List<Tag> listAllTags();

    @Select("SELECT EXISTS(SELECT * FROM tag_game WHERE game_id = #{game_id} AND tag_id = #{tag_id})")
    boolean hasTag(@Param("game_id") int gameId, @Param("tag_id") int tagId);

    @Insert("INSERT INTO tag_game (game_id, tag_id) VALUES (#{game_id}, #{tag_id})")
    void addTag(@Param("game_id") int gameId, @Param("tag_id") int tagId);

    @Delete("DELETE FROM tag_game WHERE game_id = #{game_id} AND tag_id = #{tag_id}")
    void removeTag(@Param("game_id") int gameId, @Param("tag_id") int tagId);

    @Select("SELECT id, name FROM tag INNER JOIN tag_game tg on tag.id = tg.tag_id WHERE game_id = #{game_id} ORDER BY tag_id")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name")
    })
    List<Tag> tagOfGame(@Param("game_id") int gameId);
}
