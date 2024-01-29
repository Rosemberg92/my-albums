package com.applications.albums.myalbums.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.applications.albums.myalbums.entities.Album;
import com.applications.albums.myalbums.entities.Song;
import com.applications.albums.myalbums.service.ISongService;

@Controller
@RequestMapping("/songs")
@SessionAttributes("songForAlbum")
public class SongController {

    @Autowired
    private ISongService songService;

    @GetMapping("/songs-form")
    public String songsForm(Song song, Model model, @ModelAttribute("songForAlbum")Album album){
        model.addAttribute("song", new Song());
        model.addAttribute("disc", album);
        return"admin/songsForm";
    }

    @PostMapping("/save")
    public String saveSong(Song song, RedirectAttributes redirect, Model model, @ModelAttribute("albumForSong")Album album){
        songService.saveSong(song);
        redirect.addFlashAttribute("savedSong", "Song saved successfully!");
        return "redirect:/songs/songs-form";
    }
}
