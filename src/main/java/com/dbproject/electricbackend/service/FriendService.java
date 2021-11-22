package com.dbproject.electricbackend.service;

import com.dbproject.electricbackend.exception.CustomException;
import com.dbproject.electricbackend.schema.UserSummary;

import java.sql.SQLException;
import java.util.List;

public interface FriendService {

    List<UserSummary> friendListById(int userId);

    List<UserSummary> friendListByName(String username) throws SQLException, ClassNotFoundException, CustomException;

    void addFriendById(int selfId, int otherId) throws CustomException;

    void delFriendById(int selfId, int otherId) throws CustomException;

    void addFriendByName(String selfName, String otherName) throws SQLException, ClassNotFoundException, CustomException;

    void delFriendByName(String selfName, String otherName) throws SQLException, ClassNotFoundException, CustomException;

    void addFriendByIdAndName(int selfId, String otherName) throws SQLException, ClassNotFoundException, CustomException;

    void delFriendByIdAndName(int selfId, String otherName) throws SQLException, ClassNotFoundException, CustomException;

}
