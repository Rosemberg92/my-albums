package com.applications.albums.myalbums.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.applications.albums.myalbums.service.IAlbumsService;

@Controller
public class HomeController {

    @Autowired
    private IAlbumsService albumsService;

    @GetMapping("")
    public String home(Model model) {
        model.addAttribute("albums", albumsService.listAlbums());
        return "home";
    }

}
