package com.dbproject.electricbackend.service.impl;

import com.dbproject.electricbackend.mapper.UserMapperJdbc;
import com.dbproject.electricbackend.model.entity.User;
import com.dbproject.electricbackend.model.request.UserRegister;
import com.dbproject.electricbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapperJdbc userMapper;

    @Autowired
    public UserServiceImpl(UserMapperJdbc userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<User> getAllUsers() throws SQLException, ClassNotFoundException {
        return userMapper.getAllUsers();
    }

    @Override
    public void addUser(UserRegister register) throws SQLException, ClassNotFoundException {
        userMapper.addUser(register);
    }
}
