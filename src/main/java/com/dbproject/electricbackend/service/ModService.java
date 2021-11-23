package com.dbproject.electricbackend.service;

import com.dbproject.electricbackend.exception.CustomException;
import com.dbproject.electricbackend.schema.GameMod;
import com.dbproject.electricbackend.schema.GameModCreate;

import java.util.List;

public interface ModService {
    void createMod(int userId, GameModCreate create);

    void updateMod(int userId, int modId);

    List<GameMod> modsOfGame(int gameId);

    void subscribe(int userId, int modId) throws CustomException;

    void unsubscribe(int userId, int modId) throws CustomException;

    List<GameMod> subscribedMods(int userId);
}
