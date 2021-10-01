package com.dbproject.electricbackend.service;

import com.dbproject.electricbackend.exception.CustomException;
import com.dbproject.electricbackend.schema.User;
import com.dbproject.electricbackend.schema.LoginRequest;
import com.dbproject.electricbackend.schema.RegisterRequest;
import com.dbproject.electricbackend.schema.UserSummary;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

    List<UserSummary> getUserList() throws ClassNotFoundException, SQLException;

    void addUser(RegisterRequest register) throws ClassNotFoundException, SQLException;

    int loginAndGetId(LoginRequest login) throws ClassNotFoundException, SQLException, CustomException;

    User getUserById(int userId) throws ClassNotFoundException, SQLException, CustomException;

    UserSummary getUserSummaryById(int userId) throws ClassNotFoundException, SQLException, CustomException;
}
