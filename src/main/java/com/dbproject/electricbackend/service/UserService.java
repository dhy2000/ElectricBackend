package com.dbproject.electricbackend.service;

import com.dbproject.electricbackend.exception.CustomException;
import com.dbproject.electricbackend.schema.*;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

    List<UserSummary> getUserList() throws ClassNotFoundException, SQLException;

    void addUser(RegisterRequest register) throws ClassNotFoundException, SQLException;

    int loginAndGetId(LoginRequest login) throws ClassNotFoundException, SQLException, CustomException;

    UserInfo getUserById(int userId) throws ClassNotFoundException, SQLException, CustomException;

    UserSummary getUserSummaryById(int userId) throws ClassNotFoundException, SQLException, CustomException;

    void recharge(int userId, int amount) throws CustomException, SQLException, ClassNotFoundException;

    int getBalance(int userId) throws SQLException, ClassNotFoundException, CustomException;
}
