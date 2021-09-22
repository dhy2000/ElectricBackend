package com.dbproject.electricbackend.service;

import com.dbproject.electricbackend.model.entity.User;
import com.dbproject.electricbackend.model.request.UserRegister;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

    List<User> getAllUsers() throws SQLException, ClassNotFoundException;

    void addUser(UserRegister register) throws SQLException, ClassNotFoundException;
}
