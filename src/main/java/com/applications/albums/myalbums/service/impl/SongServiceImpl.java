package com.applications.albums.myalbums.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.applications.albums.myalbums.dao.ISongDao;
import com.applications.albums.myalbums.entities.Song;
import com.applications.albums.myalbums.service.ISongService;

@Service
public class SongServiceImpl implements ISongService{

    @Autowired
    private ISongDao songDao;
    @Override
    public void saveSong(Song song) {
        songDao.save(song);
    }
    @Override
    public Song findSongById(Long id) {
        return songDao.findById(id).orElse(null);
    }
    @Override
    public void deleteSong(Long id) {
        songDao.deleteById(id);
    }

}
