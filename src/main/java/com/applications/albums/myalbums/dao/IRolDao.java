package com.applications.albums.myalbums.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.applications.albums.myalbums.entities.Rol;
import com.applications.albums.myalbums.enums.RolName;

@Repository
public interface IRolDao extends JpaRepository<Rol, Integer>{

    Optional<Rol> findByRolName (RolName rolName);
    boolean existsByRolName(RolName rolName);
}
