package com.applications.albums.myalbums.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.applications.albums.myalbums.entities.User;

//! Interfaz para el acceso a datos de la entidad User
@Repository
public interface IUserDao extends JpaRepository<User, Long>{

    //! Método para buscar un usuario por su nombre de usuario
    Optional<User> findByUsername(String username);

    //! Método para verificar si un usuario existe por su nombre de usuario
    boolean existsByUsername(String username);
}
