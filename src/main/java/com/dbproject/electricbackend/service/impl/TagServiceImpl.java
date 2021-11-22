package com.dbproject.electricbackend.service.impl;

import com.dbproject.electricbackend.exception.CustomException;
import com.dbproject.electricbackend.mapper.TagMapper;
import com.dbproject.electricbackend.schema.Tag;
import com.dbproject.electricbackend.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    private final TagMapper tagMapper;

    @Autowired
    public TagServiceImpl(TagMapper tagMapper) {
        this.tagMapper = tagMapper;
    }

    @Override
    public void createTag(String tag) {
        tagMapper.createTag(tag);
    }

    @Override
    public void deleteTag(int tagId) {
        tagMapper.deleteTag(tagId);
    }

    @Override
    public List<Tag> listAllTags() {
        return tagMapper.listAllTags();
    }

    @Override
    public void addTag(int game, int tag) throws CustomException {
        if (tagMapper.hasTag(game, tag)) {
            throw CustomException.defined(CustomException.Define.ALREADY_HAS_TAG);
        }
        tagMapper.addTag(game, tag);
    }

    @Override
    public void removeTag(int game, int tag) throws CustomException {
        if (!tagMapper.hasTag(game, tag)) {
            throw CustomException.defined(CustomException.Define.NOT_HAS_TAG);
        }
        tagMapper.removeTag(game, tag);
    }

    @Override
    public List<Tag> tagsOfGame(int game) {
        return tagMapper.tagOfGame(game);
    }
}
