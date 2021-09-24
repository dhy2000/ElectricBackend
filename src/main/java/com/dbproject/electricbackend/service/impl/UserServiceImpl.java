package com.dbproject.electricbackend.service.impl;

import com.dbproject.electricbackend.exception.CustomException;
import com.dbproject.electricbackend.exception.ExceptionDefine;
import com.dbproject.electricbackend.mapper.UserMapper;
import com.dbproject.electricbackend.model.entity.User;
import com.dbproject.electricbackend.model.request.UserRegister;
import com.dbproject.electricbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<User> getAllUsers() throws ClassNotFoundException, SQLException {
        return userMapper.getAllUsers();
    }

    @Override
    public void addUser(UserRegister register) throws ClassNotFoundException, SQLException {
        userMapper.addUser(register);
    }

    @Override
    public String getUsernameById(int userId)
            throws ClassNotFoundException, SQLException, CustomException {
        User user = userMapper.getUserById(userId);
        if (Objects.isNull(user)) {
            throw CustomException.fromDefinedTable(ExceptionDefine.NON_EXIST_USER);
        }
        return user.getUsername();
    }

    @Override
    public String getNicknameById(int userId)
            throws ClassNotFoundException, SQLException, CustomException {
        User user = userMapper.getUserById(userId);
        if (Objects.isNull(user)) {
            throw CustomException.fromDefinedTable(ExceptionDefine.NON_EXIST_USER);
        }
        return user.getUsername();
    }
}
