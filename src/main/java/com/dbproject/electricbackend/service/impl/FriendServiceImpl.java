package com.dbproject.electricbackend.service.impl;

import com.dbproject.electricbackend.exception.CustomException;
import com.dbproject.electricbackend.mapper.FriendMapper;
import com.dbproject.electricbackend.mapper.UserMapper;
import com.dbproject.electricbackend.schema.UserSummary;
import com.dbproject.electricbackend.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class FriendServiceImpl implements FriendService {

    private final UserMapper userMapper;
    private final FriendMapper friendMapper;

    @Autowired
    public FriendServiceImpl(UserMapper userMapper, FriendMapper friendMapper) {
        this.userMapper = userMapper;
        this.friendMapper = friendMapper;
    }

    @Override
    public List<UserSummary> friendListById(int userId) {
        return friendMapper.listFriends(userId);
    }

    @Override
    public List<UserSummary> friendListByName(String username) throws SQLException, ClassNotFoundException, CustomException {
        Optional<Integer> userId = userMapper.getIdOfUsername(username);
        if (userId.isPresent()) {
            return friendMapper.listFriends(userId.get());
        } else {
            throw CustomException.defined(CustomException.Define.NON_EXIST_USER);
        }
    }

    @Override
    public void addFriendById(int selfId, int otherId) throws CustomException {
        if (friendMapper.isFriend(selfId, otherId)) {
            throw CustomException.defined(CustomException.Define.ALREADY_FRIEND);
        }
        friendMapper.addFriend(selfId, otherId);
    }

    @Override
    public void delFriendById(int selfId, int otherId) throws CustomException {
        if (!friendMapper.isFriend(selfId, otherId)) {
            throw CustomException.defined(CustomException.Define.NOT_FRIEND);
        }
        friendMapper.delFriend(selfId, otherId);
    }

    @Override
    public void addFriendByName(String selfName, String otherName) throws SQLException, ClassNotFoundException, CustomException {
        Optional<Integer> selfId = userMapper.getIdOfUsername(selfName);
        Optional<Integer> otherId = userMapper.getIdOfUsername(otherName);
        if (!selfId.isPresent() || !otherId.isPresent()) {
            throw CustomException.defined(CustomException.Define.NON_EXIST_USER);
        }
        addFriendById(selfId.get(), otherId.get());
    }

    @Override
    public void delFriendByName(String selfName, String otherName) throws SQLException, ClassNotFoundException, CustomException {
        Optional<Integer> selfId = userMapper.getIdOfUsername(selfName);
        Optional<Integer> otherId = userMapper.getIdOfUsername(otherName);
        if (!selfId.isPresent() || !otherId.isPresent()) {
            throw CustomException.defined(CustomException.Define.NON_EXIST_USER);
        }
        delFriendById(selfId.get(), otherId.get());
    }

    @Override
    public void addFriendByIdAndName(int selfId, String otherName) throws SQLException, ClassNotFoundException, CustomException {
        Optional<Integer> otherId = userMapper.getIdOfUsername(otherName);
        if (!otherId.isPresent()) {
            throw CustomException.defined(CustomException.Define.NON_EXIST_USER);
        }
        addFriendById(selfId, otherId.get());
    }

    @Override
    public void delFriendByIdAndName(int selfId, String otherName) throws SQLException, ClassNotFoundException, CustomException {
        Optional<Integer> otherId = userMapper.getIdOfUsername(otherName);
        if (!otherId.isPresent()) {
            throw CustomException.defined(CustomException.Define.NON_EXIST_USER);
        }
        delFriendById(selfId, otherId.get());
    }

}
