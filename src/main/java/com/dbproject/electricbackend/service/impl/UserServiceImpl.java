package com.dbproject.electricbackend.service.impl;

import com.dbproject.electricbackend.exception.CustomException;
import com.dbproject.electricbackend.mapper.UserMapper;
import com.dbproject.electricbackend.schema.User;
import com.dbproject.electricbackend.schema.LoginRequest;
import com.dbproject.electricbackend.schema.RegisterRequest;
import com.dbproject.electricbackend.schema.UserSummary;
import com.dbproject.electricbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<UserSummary> getUserList() throws ClassNotFoundException, SQLException {
        return userMapper.getUserList();
    }

    @Override
    public void addUser(RegisterRequest register) throws ClassNotFoundException, SQLException {
        userMapper.addUser(register);
    }

    @Override
    public int loginAndGetId(LoginRequest login) throws ClassNotFoundException, SQLException, CustomException {
        Optional<UserSummary> user = userMapper.getUserSummaryWithNameAndPassword(login.getUsername(), login.getPassword());
        if (user.isPresent()) {
            return user.get().getId();
        } else {
            throw CustomException.defined(CustomException.Define.WRONG_USERNAME_PASSWORD);
        }
    }

    @Override
    public User getUserById(int userId) throws ClassNotFoundException, SQLException, CustomException {
        Optional<User> user = userMapper.getUserById(userId);
        if (!user.isPresent()) {
            throw CustomException.defined(CustomException.Define.NON_EXIST_USER);
        }
        return user.get();
    }

    @Override
    public UserSummary getUserSummaryById(int userId) throws ClassNotFoundException, SQLException, CustomException {
        Optional<UserSummary> user = userMapper.getUserSummaryById(userId);
        if (!user.isPresent()) {
            throw CustomException.defined(CustomException.Define.NON_EXIST_USER);
        }
        return user.get();
    }
}
