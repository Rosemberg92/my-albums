package com.applications.albums.myalbums.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.applications.albums.myalbums.entities.Comment;

//! Interfaz para el acceso a datos de la entidad Comment
@Repository
public interface ICommentDao extends JpaRepository<Comment, Long>{

}
