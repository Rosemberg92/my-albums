package com.applications.albums.myalbums.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.applications.albums.myalbums.dao.IRolDao;
import com.applications.albums.myalbums.entities.Rol;
import com.applications.albums.myalbums.enums.RolName;

@Service
public class RolService {

    @Autowired
    private IRolDao rolDao;

    public void saveRol(Rol rol) {
        rolDao.save(rol);
    }

    public Optional<Rol> getByRolName (RolName rolName){
        return rolDao.findByRolName(rolName);
    }

    public boolean existsByRolName(RolName rolName){
        return rolDao.existsByRolName(rolName);
    }
}
