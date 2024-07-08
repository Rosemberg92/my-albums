package com.applications.albums.myalbums.controllers;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.applications.albums.myalbums.entities.Rol;
import com.applications.albums.myalbums.entities.User;
import com.applications.albums.myalbums.enums.RolName;
import com.applications.albums.myalbums.security.service.RolService;
import com.applications.albums.myalbums.security.service.UserService;



@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RolService rolService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String registerUser(){
        return "register";
    }

    @PostMapping("/save")
    public String saveUser(String username, String password,RedirectAttributes redirect){
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));

        Rol rolUser = rolService.getByRolName(RolName.ROLE_USER).get();
        Rol rolAdmin = rolService.getByRolName(RolName.ROLE_ADMIN).get();
        Set<Rol> roles= new HashSet<Rol>();
        roles.add(rolUser);
        roles.add(rolAdmin);

        user.setRoles(roles);
        userService.saveUser(user);

        redirect.addFlashAttribute("success", "Usuario guardado con éxito");

        return "redirect:/login";
    }

}
