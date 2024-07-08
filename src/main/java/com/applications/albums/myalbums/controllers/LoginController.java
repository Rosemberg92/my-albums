package com.applications.albums.myalbums.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    //! Método para mostrar el formulario de login
    @GetMapping("/login")
    public String loginForm(){
        return "login";
    }

}
