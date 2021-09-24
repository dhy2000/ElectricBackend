package com.dbproject.electricbackend.service.impl;

import com.dbproject.electricbackend.exception.CustomException;
import com.dbproject.electricbackend.mapper.UserMapper;
import com.dbproject.electricbackend.model.entity.User;
import com.dbproject.electricbackend.model.request.UserLogin;
import com.dbproject.electricbackend.model.request.UserRegister;
import com.dbproject.electricbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
    public int loginAndGetId(UserLogin login) throws ClassNotFoundException, SQLException, CustomException {
        Optional<User> user = userMapper.getUserWithNameAndPassword(login.getUsername(), login.getPassword());
        if (user.isPresent()) {
            return user.get().getId();
        } else {
            throw CustomException.defined(CustomException.Define.WRONG_USERNAME_PASSWORD);
        }
    }

    @Override
    public String getUsernameById(int userId)
            throws ClassNotFoundException, SQLException, CustomException {
        Optional<User> user = userMapper.getUserById(userId);
        if (!user.isPresent()) {
            throw CustomException.defined(CustomException.Define.NON_EXIST_USER);
        }
        return user.get().getUsername();
    }

    @Override
    public String getNicknameById(int userId)
            throws ClassNotFoundException, SQLException, CustomException {
        Optional<User> user = userMapper.getUserById(userId);
        if (!user.isPresent()) {
            throw CustomException.defined(CustomException.Define.NON_EXIST_USER);
        }
        return user.get().getUsername();
    }

    @Override
    public User getUserById(int userId) throws ClassNotFoundException, SQLException, CustomException {
        Optional<User> user = userMapper.getUserById(userId);
        if (!user.isPresent()) {
            throw CustomException.defined(CustomException.Define.NON_EXIST_USER);
        }
        return user.get();
    }
}
