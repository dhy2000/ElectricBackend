package com.dbproject.electricbackend.service;

import com.dbproject.electricbackend.model.entity.User;
import com.dbproject.electricbackend.model.request.UserRegister;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    void addUser(UserRegister register);
}
