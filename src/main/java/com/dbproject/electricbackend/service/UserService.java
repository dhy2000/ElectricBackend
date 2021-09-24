package com.dbproject.electricbackend.service;

import com.dbproject.electricbackend.exception.CustomException;
import com.dbproject.electricbackend.model.entity.User;
import com.dbproject.electricbackend.model.request.UserLogin;
import com.dbproject.electricbackend.model.request.UserRegister;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

    List<User> getAllUsers() throws ClassNotFoundException, SQLException;

    void addUser(UserRegister register) throws ClassNotFoundException, SQLException;

    int loginAndGetId(UserLogin login) throws ClassNotFoundException, SQLException, CustomException;

    String getUsernameById(int userId) throws ClassNotFoundException, SQLException, CustomException;

    String getNicknameById(int userId) throws ClassNotFoundException, SQLException, CustomException;

    User getUserById(int userId) throws ClassNotFoundException, SQLException, CustomException;
}
