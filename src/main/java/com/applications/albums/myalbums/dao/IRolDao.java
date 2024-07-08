package com.applications.albums.myalbums.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.applications.albums.myalbums.entities.Rol;
import com.applications.albums.myalbums.enums.RolName;

//! Interfaz para el acceso a datos de la entidad Rol
@Repository
public interface IRolDao extends JpaRepository<Rol, Integer>{

    //! Método para buscar un rol por su nombre de rol
    Optional<Rol> findByRolName (RolName rolName);

    //! Método para verificar si un rol existe por su nombre de rol
    boolean existsByRolName(RolName rolName);
}
