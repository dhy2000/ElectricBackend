package com.dbproject.electricbackend.service;

import com.dbproject.electricbackend.schema.Comment;
import com.dbproject.electricbackend.schema.CommentCreate;

import java.util.List;

public interface CommentService {
    void createComment(int userId, CommentCreate create);

    void deleteComment(int userId, int commentId);

    List<Comment> listCommentOfGame(int gameId);
}
