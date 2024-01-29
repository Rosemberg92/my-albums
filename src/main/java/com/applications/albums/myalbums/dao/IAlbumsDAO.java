package com.applications.albums.myalbums.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.applications.albums.myalbums.entities.Album;



@Repository
public interface IAlbumsDAO extends JpaRepository<Album, Long>{
}
