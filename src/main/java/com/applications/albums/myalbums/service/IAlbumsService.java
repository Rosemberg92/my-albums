package com.applications.albums.myalbums.service;

import java.util.List;

import com.applications.albums.myalbums.entities.Album;

public interface IAlbumsService {
    void save(Album album);
    List<Album> listAlbums();
    Album albumById(Long id);
}