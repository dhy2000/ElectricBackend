package com.dbproject.electricbackend.mapper;

import com.dbproject.electricbackend.model.entity.User;
import com.dbproject.electricbackend.model.request.UserRegister;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

//@Mapper
public interface UserMapper {

//    @Select("select id, username, nickname from user")
//    @Results({
//            @Result(property = "id", column = "id"),
//            @Result(property = "username", column = "username", jdbcType = JdbcType.VARCHAR),
//            @Result(property = "nickname", column = "nickname", jdbcType = JdbcType.VARCHAR)
//    })
    List<User> getAllUsers() throws ClassNotFoundException, SQLException;

//    @Insert("insert into user(username, password, nickname) values(#{username}, #{password}, #{nickname})")
    void addUser(UserRegister register) throws ClassNotFoundException, SQLException;

    Optional<User> getUserById(int id) throws ClassNotFoundException, SQLException;

    Optional<User> getUserWithNameAndPassword(String username, String password) throws ClassNotFoundException, SQLException;
}
