package com.applications.albums.myalbums.service.impl;

import java.util.List;

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

    @Override
    public List<Album> listAlbums() {
        return albumsDAO.findAll();
    }

    @Override
    public Album albumById(Long id) {
        return albumsDAO.findById(id).orElse(null);
    }

}
