package com.applications.albums.myalbums.service;

import com.applications.albums.myalbums.entities.Comment;

public interface ICommentService {
    void saveComment(Comment comment);
    Comment getCommentById(Long id);
    void deleteComment(Long id);
}
