package com.dbproject.electricbackend.service.impl;

import com.dbproject.electricbackend.exception.CustomException;
import com.dbproject.electricbackend.mapper.UserMapper;
import com.dbproject.electricbackend.schema.*;
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
    public UserInfo getUserById(int userId) throws ClassNotFoundException, SQLException, CustomException {
        Optional<UserInfo> user = userMapper.getUserById(userId);
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

    @Override
    public void recharge(int userId, int amount) throws CustomException, SQLException, ClassNotFoundException {
        if (amount <= 0) {
            throw CustomException.defined(CustomException.Define.ILLEGAL_RECHARGE_AMOUNT);
        }
        userMapper.recharge(userId, amount);
    }

    @Override
    public int getBalance(int userId) throws SQLException, ClassNotFoundException, CustomException {
        Optional<Integer> balance = userMapper.getBalance(userId);
        if (!balance.isPresent()) {
            throw CustomException.defined(CustomException.Define.NON_EXIST_USER);
        }
        return balance.get();
    }
}
