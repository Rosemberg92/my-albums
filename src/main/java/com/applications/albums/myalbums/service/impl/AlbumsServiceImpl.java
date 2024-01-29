package com.applications.albums.myalbums.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.applications.albums.myalbums.dao.IAlbumsDAO;
import com.applications.albums.myalbums.entities.Album;
import com.applications.albums.myalbums.service.IAlbumsService;

@Service
public class AlbumsServiceImpl implements IAlbumsService{


    @Autowired
    private IAlbumsDAO albumsDAO;
    @Override
    public void save(Album album) {
       albumsDAO.save(album);
    }

}
