package com.applications.albums.myalbums.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.applications.albums.myalbums.dao.ICommentDao;
import com.applications.albums.myalbums.entities.Comment;
import com.applications.albums.myalbums.service.ICommentService;

@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private ICommentDao commentDao;

    @Override
    public void saveComment(Comment comment) {
        commentDao.save(comment);
    }

}
