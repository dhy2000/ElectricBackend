package com.dbproject.electricbackend.mapper;

import com.dbproject.electricbackend.model.entity.User;
import com.dbproject.electricbackend.model.request.UserRegister;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Component
public class UserMapperJdbc {

    @Value("${spring.datasource.url}")
    private String dbAddress;

    @Value("${spring.datasource.driver-class-name}")
    private String dbDriverName;

    @Value("${spring.datasource.username}")
    private String dbUserName;

    @Value("${spring.datasource.password}")
    private String dbPassword;


    public List<User> getAllUsers() throws SQLException, ClassNotFoundException {
        // select id, username, nickname from user

        Connection conn = null;
        PreparedStatement query = null;
        ResultSet result = null;
        List<User> userList = new LinkedList<>();
        try {
            Class.forName(dbDriverName);
            conn = DriverManager.getConnection(dbAddress, dbUserName, dbPassword);
            String sql = "SELECT id, username, nickname FROM user";
            query = conn.prepareStatement(sql);
            result = query.executeQuery();
            while (result.next()) {
                Integer id = result.getInt("id");
                String username = result.getString("username");
                String nickname = result.getString("nickname");
                userList.add(new User(id, username, nickname));
            }
            return userList;
        } finally {
            close(conn, query, result);
        }
    }

    public void addUser(UserRegister register) throws ClassNotFoundException, SQLException {
        // insert into user(username, password, nickname) values(#{username}, #{password}, #{nickname})
        Connection conn = null;
        PreparedStatement query = null;
        try {
            Class.forName(dbDriverName);
            conn = DriverManager.getConnection(dbAddress, dbUserName, dbPassword);
            String sql = "INSERT INTO user (username, password, nickname) VALUES (?, ?, ?)";
            query = conn.prepareStatement(sql);
            query.setString(1, register.getUsername());
            query.setString(2, register.getPassword());
            query.setString(3, register.getNickname());
            query.executeUpdate();
        } finally {
            close(conn, query, null);
        }
    }

    public void close(Connection conn, PreparedStatement pst, ResultSet result) throws SQLException {
        if (Objects.nonNull(result)) {
            result.close();
        }
        if (Objects.nonNull(pst)) {
            pst.close();
        }
        if (Objects.nonNull(conn)) {
            conn.close();
        }
    }
}
