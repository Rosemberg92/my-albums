package com.applications.albums.myalbums.service;

import com.applications.albums.myalbums.entities.Song;

public interface ISongService {
    void saveSong(Song song);
    Song findSongById(Long id);
    void deleteSong(Long id);
}
