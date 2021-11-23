package com.dbproject.electricbackend.service.impl;

import com.dbproject.electricbackend.exception.CustomException;
import com.dbproject.electricbackend.mapper.ModMapper;
import com.dbproject.electricbackend.schema.GameMod;
import com.dbproject.electricbackend.schema.GameModCreate;
import com.dbproject.electricbackend.service.ModService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ModServiceImpl implements ModService {
    private final ModMapper modMapper;

    @Autowired
    public ModServiceImpl(ModMapper modMapper) {
        this.modMapper = modMapper;
    }

    @Override
    public void createMod(int userId, GameModCreate create) {
        Timestamp now = new Timestamp(new java.util.Date().getTime());
        modMapper.createMod(create.getName(), create.getDescribe(), userId, now, create.getGameId());
    }

    @Override
    public void updateMod(int userId, int modId) {
        Timestamp now = new Timestamp(new java.util.Date().getTime());
        modMapper.updateMod(userId, modId, now);
    }

    @Override
    public List<GameMod> modsOfGame(int gameId) {
        return modMapper.modsOfGame(gameId);
    }

    @Override
    public void subscribe(int userId, int modId) throws CustomException {
        if (modMapper.isSubscribed(userId, modId)) {
            throw CustomException.defined(CustomException.Define.MOD_ALREADY_SUBSCRIBE);
        }
        modMapper.subscribe(userId, modId);
    }

    @Override
    public void unsubscribe(int userId, int modId) throws CustomException {
        if (!modMapper.isSubscribed(userId, modId)) {
            throw CustomException.defined(CustomException.Define.MOD_NOT_SUBSCRIBE);
        }
        modMapper.unsubscribe(userId, modId);
    }

    @Override
    public List<GameMod> subscribedMods(int userId) {
        return modMapper.subscribedMods(userId);
    }
}
