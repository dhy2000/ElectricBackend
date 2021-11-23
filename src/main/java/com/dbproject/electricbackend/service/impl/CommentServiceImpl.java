package com.dbproject.electricbackend.service.impl;

import com.dbproject.electricbackend.mapper.CommentMapper;
import com.dbproject.electricbackend.schema.Comment;
import com.dbproject.electricbackend.schema.CommentCreate;
import com.dbproject.electricbackend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentMapper commentMapper;

    @Autowired
    public CommentServiceImpl(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Override
    public void createComment(int userId, CommentCreate create) {
        Timestamp now = new Timestamp(new java.util.Date().getTime());
        commentMapper.createComment(create.getGameId(), userId, create.getTitle(), create.getContent(), now, create.getReply());
    }

    @Override
    public void deleteComment(int userId, int commentId) {
        commentMapper.deleteComment(commentId, userId);
    }

    @Override
    public List<Comment> listCommentOfGame(int gameId) {
        return commentMapper.listCommentsOfGame(gameId);
    }
}
