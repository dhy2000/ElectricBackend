package com.dbproject.electricbackend.service;

import com.dbproject.electricbackend.exception.CustomException;
import com.dbproject.electricbackend.model.entity.User;
import com.dbproject.electricbackend.model.request.UserRegister;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

    List<User> getAllUsers() throws ClassNotFoundException, SQLException;

    void addUser(UserRegister register) throws ClassNotFoundException, SQLException;

    String getUsernameById(int userId) throws ClassNotFoundException, SQLException, CustomException;

    String getNicknameById(int userId) throws ClassNotFoundException, SQLException, CustomException;
}
