package com.applications.albums.myalbums.security.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.applications.albums.myalbums.dao.IUserDao;
import com.applications.albums.myalbums.entities.User;

@Service
public class UserService {
    @Autowired
    private IUserDao userDao;

    public List<User> userList() {
        return userDao.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userDao.findById(id);
    }

    public Optional<User> getByUsername (String username) {
        return userDao.findByUsername(username);
    }

    public void saveUser(User user){
        userDao.save(user);
    }

    public boolean existById(Long id) {
        return userDao.existsById(id);
    }

    public boolean existsByUsername(String username) {
        return userDao.existsByUsername(username);
    }
}