package com.dbproject.electricbackend.service.impl;

import com.dbproject.electricbackend.config.FileConfiguration;
import com.dbproject.electricbackend.exception.CustomException;
import com.dbproject.electricbackend.mapper.UserGameMapper;
import com.dbproject.electricbackend.mapper.UserMapper;
import com.dbproject.electricbackend.schema.*;
import com.dbproject.electricbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final String IMAGE_URI_PREFIX = "/file/image/";

    private final Path storage;
    private final UserMapper userMapper;
    private final UserGameMapper userGameMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, UserGameMapper userGameMapper, FileConfiguration config) {
        this.userMapper = userMapper;
        this.userGameMapper = userGameMapper;
        this.storage = Paths.get(config.getFileDir()).toAbsolutePath().normalize();
    }

    @Override
    public List<UserSummary> getUserList() throws ClassNotFoundException, SQLException {
        return userMapper.getUserList();
    }

    @Override
    public void addUser(RegisterRequest register) throws ClassNotFoundException, SQLException {
        userMapper.addUser(register);
    }

    @Override
    public int loginAndGetId(LoginRequest login) throws ClassNotFoundException, SQLException, CustomException {
        Optional<UserSummary> user = userMapper.getUserSummaryWithNameAndPassword(login.getUsername(), login.getPassword());
        if (user.isPresent()) {
            return user.get().getId();
        } else {
            throw CustomException.defined(CustomException.Define.WRONG_USERNAME_PASSWORD);
        }
    }

    @Override
    public UserProfile getUserProfileById(int userId) throws ClassNotFoundException, SQLException, CustomException {
        Optional<UserProfile> user = userMapper.getUserProfileById(userId);
        if (!user.isPresent()) {
            throw CustomException.defined(CustomException.Define.NON_EXIST_USER);
        }
        return user.get();
    }

    @Override
    public UserSummary getUserSummaryById(int userId) throws ClassNotFoundException, SQLException, CustomException {
        Optional<UserSummary> user = userMapper.getUserSummaryById(userId);
        if (!user.isPresent()) {
            throw CustomException.defined(CustomException.Define.NON_EXIST_USER);
        }
        return user.get();
    }

    @Override
    public void recharge(int userId, int amount) throws CustomException, SQLException, ClassNotFoundException {
        if (amount <= 0) {
            throw CustomException.defined(CustomException.Define.ILLEGAL_RECHARGE_AMOUNT);
        }
        userMapper.recharge(userId, amount);
    }

    @Override
    public int getBalance(int userId) throws SQLException, ClassNotFoundException, CustomException {
        Optional<Integer> balance = userMapper.getBalance(userId);
        if (!balance.isPresent()) {
            throw CustomException.defined(CustomException.Define.NON_EXIST_USER);
        }
        return balance.get();
    }

    @Override
    public void setAvatar(int userId, MultipartFile image) throws CustomException, SQLException, ClassNotFoundException {
        String realFileName = "avatar_" + userId + ".png";
        if (realFileName.contains("..")) {
            throw CustomException.defined(CustomException.Define.INVALID_FILE_NAME);
        }
        Path target = storage.resolve(realFileName);
        try {
            Files.copy(image.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw CustomException.defined(CustomException.Define.FILE_CREATE_ERROR);
        }
        String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(IMAGE_URI_PREFIX).path(realFileName).toUriString();
        userMapper.setAvatar(userId, url);
    }

    @Override
    public String getAvatar(int userId) throws SQLException, ClassNotFoundException, CustomException {
        Optional<String> url = userMapper.getAvatar(userId);
        return url.orElse("");
    }

    @Override
    public void updateProfile(UserProfileUpdate profile) throws SQLException, ClassNotFoundException {
        userMapper.updateProfile(profile);
    }

    @Override
    public void setPassword(int userId, PasswordUpdate password) throws SQLException, ClassNotFoundException, CustomException {
        boolean verify = userMapper.hasUserWithPassword(userId, password.getOldPassword());
        if (!verify) {
            throw CustomException.defined(CustomException.Define.WRONG_OLD_PASSWORD);
        } else {
            userMapper.updatePassword(userId, password.getNewPassword());
        }
    }

    @Override
    public List<GameOfUser> getGamesOfUser(int userId) {
        return userGameMapper.gameOfUser(userId);
    }
}
