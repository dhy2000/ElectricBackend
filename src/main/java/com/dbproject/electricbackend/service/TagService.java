package com.dbproject.electricbackend.service;

import com.dbproject.electricbackend.exception.CustomException;
import com.dbproject.electricbackend.schema.Tag;

import java.util.List;

public interface TagService {
    void createTag(String tag);

    void deleteTag(int tagId);

    List<Tag> listAllTags();

    void addTag(int game, int tag) throws CustomException;

    void removeTag(int game, int tag) throws CustomException;

    List<Tag> tagsOfGame(int game);
}
