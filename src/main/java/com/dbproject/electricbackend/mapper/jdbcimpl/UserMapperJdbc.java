package com.dbproject.electricbackend.mapper.jdbcimpl;

import com.dbproject.electricbackend.exception.CustomException;
import com.dbproject.electricbackend.mapper.UserMapper;
import com.dbproject.electricbackend.schema.UserProfile;
import com.dbproject.electricbackend.schema.RegisterRequest;
import com.dbproject.electricbackend.schema.UserProfileUpdate;
import com.dbproject.electricbackend.schema.UserSummary;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Log4j2
@Component
public class UserMapperJdbc implements UserMapper {

    @Value("${spring.datasource.url}")
    private String dbAddress;

    @Value("${spring.datasource.driver-class-name}")
    private String dbDriverName;

    @Value("${spring.datasource.username}")
    private String dbUserName;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    @Override
    public List<UserSummary> getUserList() throws ClassNotFoundException, SQLException {
        // select id, username, nickname from user
        Connection conn = null;
        PreparedStatement query = null;
        ResultSet result = null;
        List<UserSummary> userList = new LinkedList<>();
        try {
            Class.forName(dbDriverName);
            conn = DriverManager.getConnection(dbAddress, dbUserName, dbPassword);
//            String sql = "SELECT `id`, `username`, `nickname`, `signature`, `birthday`, `email`, `phone`, `balance`  FROM `user`;";
            String sql = "SELECT `id`, `username`, `nickname` FROM `user`";
            query = conn.prepareStatement(sql);
            result = query.executeQuery();
            while (result.next()) {
                Integer id = result.getInt("id");
                String username = result.getString("username");
                String nickname = result.getString("nickname");
                userList.add(new UserSummary(id, username, nickname));
            }
            return userList;
        } finally {
            close(conn, query, result);
        }
    }

    @Override
    public Optional<Integer> getIdOfUsername(String username) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement query = null;
        ResultSet result = null;
        try {
            Class.forName(dbDriverName);
            conn = DriverManager.getConnection(dbAddress, dbUserName, dbPassword);
            String sql = "SELECT `id` FROM `user` WHERE `username` = ?";
            query = conn.prepareStatement(sql);
            query.setString(1, username);
            result = query.executeQuery();
            if (result.next()) {
                return Optional.of(result.getInt("id"));
            }
            return Optional.empty();
        } finally {
            close(conn, query, result);
        }
    }

    @Override
    public void addUser(RegisterRequest register) throws ClassNotFoundException, SQLException {
        // insert into user(username, password, nickname) values(#{username}, #{password}, #{nickname})
        Connection conn = null;
        PreparedStatement query = null;
        try {
            Class.forName(dbDriverName);
            conn = DriverManager.getConnection(dbAddress, dbUserName, dbPassword);
            String sql = "INSERT INTO user (`username`, `password`, `nickname`, `signature`, `birthday`, `email`, `phone`) VALUES (?, ?, ?, '', ?, ?, ?)";
            query = conn.prepareStatement(sql);
            query.setString(1, register.getUsername());
            query.setString(2, register.getPassword());
            query.setString(3, register.getNickname());
            query.setDate(4, new Date(register.getBirthday().getTime()));
            query.setString(5, register.getEmail());
            query.setString(6, register.getPhone());
            log.info(query);
            query.executeUpdate();
        } finally {
            close(conn, query, null);
        }
    }

    @Override
    public Optional<UserSummary> getUserSummaryById(int id) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement query = null;
        ResultSet result = null;
        try {
            Class.forName(dbDriverName);
            conn = DriverManager.getConnection(dbAddress, dbUserName, dbPassword);
            String sql = "SELECT id, username, nickname FROM user WHERE id = ?";
            query = conn.prepareStatement(sql);
            query.setInt(1, id);
            result = query.executeQuery();
            return getSingleUserSummaryFromResult(result);
        } finally {
            close(conn, query, result);
        }
    }

    @Override
    public Optional<UserProfile> getUserProfileById(int userId) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement query = null;
        ResultSet result = null;
        try {
            Class.forName(dbDriverName);
            conn = DriverManager.getConnection(dbAddress, dbUserName, dbPassword);
            String sql = "SELECT `id`, `username`, `nickname`, `signature`, `birthday`, `email`, `phone`, `balance`  FROM `user` WHERE `id` = ?";
            query = conn.prepareStatement(sql);
            query.setInt(1, userId);
            result = query.executeQuery();
            if (result.next()) {
                Integer id = result.getInt("id");
                String username = result.getString("username");
                String nickname = result.getString("nickname");
                String signature = result.getString("signature");
                Date birthday = result.getDate("birthday");
                String email = result.getString("email");
                String phone = result.getString("phone");
                Integer balance = result.getInt("balance");
                return Optional.of(new UserProfile(id, username, nickname, signature, birthday, email, phone, balance));
            } else {
                return Optional.empty();
            }
        } finally {
            close(conn, query, result);
        }
    }

    @Override
    public Optional<UserSummary> getUserSummaryWithNameAndPassword(String username, String password) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement query = null;
        ResultSet result = null;
        try {
            Class.forName(dbDriverName);
            conn = DriverManager.getConnection(dbAddress, dbUserName, dbPassword);
            String sql = "SELECT id, username, nickname FROM user WHERE username = ? AND password = ?";
            query = conn.prepareStatement(sql);
            query.setString(1, username);
            query.setString(2, password);
            result = query.executeQuery();
            return getSingleUserSummaryFromResult(result);
        } finally {
            close(conn, query, result);
        }
    }

    @Override
    public void recharge(int userId, int amount) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement query = null;
        try {
            Class.forName(dbDriverName);
            conn = DriverManager.getConnection(dbAddress, dbUserName, dbPassword);
            String sql = "UPDATE `user` SET `balance` = `balance` + ? WHERE `id` = ?";
            query = conn.prepareStatement(sql);
            query.setInt(1, amount);
            query.setInt(2, userId);
            query.executeUpdate();
        } finally {
            close(conn, query, null);
        }
    }

    @Override
    public Optional<Integer> getBalance(int userId) throws ClassNotFoundException, SQLException, CustomException {
        Connection conn = null;
        PreparedStatement query = null;
        ResultSet result = null;
        try {
            Class.forName(dbDriverName);
            conn = DriverManager.getConnection(dbAddress, dbUserName, dbPassword);
            String sql = "SELECT `balance` FROM `user` WHERE `id` = ?";
            query = conn.prepareStatement(sql);
            query.setInt(1, userId);
            result = query.executeQuery();
            if (result.next()) {
                Integer balance = result.getInt("balance");
                return Optional.of(balance);
            } else {
                throw CustomException.defined(CustomException.Define.NON_EXIST_USER);
            }
        } finally {
            close(conn, query, result);
        }
    }

    @Override
    public void consume(int userId, int amount) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement query = null;
        try {
            Class.forName(dbDriverName);
            conn = DriverManager.getConnection(dbAddress, dbUserName, dbPassword);
            String sql = "UPDATE `user` SET `balance` = `balance` - ? WHERE `id` = ? AND `balance` >= ?";
            query = conn.prepareStatement(sql);
            query.setInt(1, amount);
            query.setInt(2, userId);
            query.setInt(3, amount);
            query.executeUpdate();
        } finally {
            close(conn, query, null);
        }
    }

    @Override
    public void setAvatar(int userId, String url) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement query = null;
        try {
            Class.forName(dbDriverName);
            conn = DriverManager.getConnection(dbAddress, dbUserName, dbPassword);
            String sql = "UPDATE `user` SET `avatar` = ? WHERE `id` = ?";
            query = conn.prepareStatement(sql);
            query.setString(1, url);
            query.setInt(2, userId);
            query.executeUpdate();
        } finally {
            close(conn, query, null);
        }
    }

    @Override
    public Optional<String> getAvatar(int userId) throws ClassNotFoundException, SQLException, CustomException {
        Connection conn = null;
        PreparedStatement query = null;
        ResultSet result = null;
        try {
            Class.forName(dbDriverName);
            conn = DriverManager.getConnection(dbAddress, dbUserName, dbPassword);
            String sql = "SELECT `avatar` FROM `user` WHERE `id` = ?";
            query = conn.prepareStatement(sql);
            query.setInt(1, userId);
            result = query.executeQuery();
            if (result.next()) {
                String avatar = result.getString("avatar");
                return Optional.ofNullable(avatar);
            } else {
                throw CustomException.defined(CustomException.Define.NON_EXIST_USER);
            }
        } finally {
            close(conn, query, result);
        }
    }

    @Override
    public void updateProfile(UserProfileUpdate profile) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement query = null;
        try {
            Class.forName(dbDriverName);
            conn = DriverManager.getConnection(dbAddress, dbUserName, dbPassword);
            String sql = "UPDATE `user` SET `nickname` = ?, `signature` = ?, `birthday` = ?, `email` = ?, `phone` = ? WHERE `id` = ?";
            query = conn.prepareStatement(sql);
            query.setString(1, profile.getNickname());
            query.setString(2, profile.getSignature());
            query.setDate(3, new Date(profile.getBirthday().getTime()));
            query.setString(4, profile.getEmail());
            query.setString(5, profile.getPhone());
            query.setInt(6, profile.getId());
            query.executeUpdate();
        } finally {
            close(conn, query, null);
        }
    }

    @Override
    public boolean hasUserWithPassword(int userId, String password) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement query = null;
        ResultSet result = null;
        try {
            Class.forName(dbDriverName);
            conn = DriverManager.getConnection(dbAddress, dbUserName, dbPassword);
            String sql = "SELECT `id` FROM `user` WHERE `id` = ? AND `password` = ?";
            query = conn.prepareStatement(sql);
            query.setInt(1, userId);
            query.setString(2, password);
            result = query.executeQuery();
            return result.next();
        } finally {
            close(conn, query, result);
        }
    }

    @Override
    public void updatePassword(int userId, String password) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement query = null;
        try {
            Class.forName(dbDriverName);
            conn = DriverManager.getConnection(dbAddress, dbUserName, dbPassword);
            String sql = "UPDATE `user` SET `password` = ? WHERE `id` = ?";
            query = conn.prepareStatement(sql);
            query.setString(1, password);
            query.setInt(2, userId);
            query.executeUpdate();
        } finally {
            close(conn, query, null);
        }
    }

    private Optional<UserSummary> getSingleUserSummaryFromResult(ResultSet result) throws SQLException {
        if (result.next()) {
            Integer id = result.getInt("id");
            String username = result.getString("username");
            String nickname = result.getString("nickname");
            return Optional.of(new UserSummary(id, username, nickname));
        }
        return Optional.empty();
    }

    private void close(Connection conn, PreparedStatement pst, ResultSet result) throws SQLException {
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
