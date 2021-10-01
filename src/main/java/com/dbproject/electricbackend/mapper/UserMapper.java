package com.dbproject.electricbackend.mapper;

import com.dbproject.electricbackend.schema.User;
import com.dbproject.electricbackend.schema.RegisterRequest;
import com.dbproject.electricbackend.schema.UserSummary;

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
    List<UserSummary> getUserList() throws ClassNotFoundException, SQLException;

//    @Insert("insert into user(username, password, nickname) values(#{username}, #{password}, #{nickname})")
    void addUser(RegisterRequest register) throws ClassNotFoundException, SQLException;

    Optional<UserSummary> getUserSummaryById(int id) throws ClassNotFoundException, SQLException;

    Optional<User> getUserById(int id) throws ClassNotFoundException, SQLException;

    Optional<UserSummary> getUserSummaryWithNameAndPassword(String username, String password) throws ClassNotFoundException, SQLException;
}
