package com.applications.albums.myalbums.controllers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.applications.albums.myalbums.entities.Album;
import com.applications.albums.myalbums.service.IAlbumsService;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IAlbumsService albumsService;

    @GetMapping("/album-form")
    public String albumFrom(Model model) {
        model.addAttribute("album", new Album());
        return "admin/albumForm";
    }

    @PostMapping("/save-album")
    public String saveAlbum(@RequestParam(name = "file", required = false) MultipartFile folder, Album album,
            RedirectAttributes feedbackMessage) {

        if (!folder.isEmpty()) {

            String route = "C://Temp//uploads";
            String uniqName = UUID.randomUUID() + " " + folder.getOriginalFilename();

            try {
                byte[] bytes = folder.getBytes();
                Path absolutePath = Paths.get(route + "//" + uniqName);
                Files.write(absolutePath, bytes);
                album.setFolder(uniqName);

                albumsService.save(album);
                feedbackMessage.addFlashAttribute("success", "Album saved successfully");

            } catch (Exception e) {
                e.getCause().getMessage();
            }
        }
        return "redirect:/admin/album-form";
    }
}
