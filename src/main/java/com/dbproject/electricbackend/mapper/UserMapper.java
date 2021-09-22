package com.dbproject.electricbackend.mapper;

import com.dbproject.electricbackend.model.entity.User;
import com.dbproject.electricbackend.model.request.UserRegister;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select id, username, nickname from user")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "username", jdbcType = JdbcType.VARCHAR),
            @Result(property = "nickname", column = "nickname", jdbcType = JdbcType.VARCHAR)
    })
    List<User> getAllUsers();

    @Insert("insert into user(username, password, nickname) values(#{username}, #{password}, #{nickname})")
    void addUser(UserRegister register);
}
