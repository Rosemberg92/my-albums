package com.applications.albums.myalbums.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.applications.albums.myalbums.entities.Album;
import com.applications.albums.myalbums.entities.Song;
import com.applications.albums.myalbums.service.IAlbumsService;
import com.applications.albums.myalbums.service.ISongService;

@Controller
@RequestMapping("/songs")
@SessionAttributes("songForAlbum")
public class SongController {

    @Autowired
    private ISongService songService;

    @Autowired
    private IAlbumsService albumsService;

    //! Método para mostrar el formulario de creación de canciones
    @GetMapping("/songs-form")
    public String songsForm(Song song, Model model, @ModelAttribute("songForAlbum")Album album){
        model.addAttribute("song", new Song());
        model.addAttribute("disc", album);
        return"admin/songsForm";
    }

    //! Método para guardar una canción
    @PostMapping("/save")
    public String saveSong(Song song, RedirectAttributes redirect, Model model, @ModelAttribute("albumForSong")Album album){
        songService.saveSong(song);
        redirect.addFlashAttribute("savedSong", "Canción añadida correctamente!");
        return "redirect:/songs/songs-form";
    }

    @GetMapping("/add-songs/{id}")
    public String addSong(@PathVariable Long id, Model model){

        Album album = albumsService.albumById(id);
        model.addAttribute("song", new Song());
        model.addAttribute("track", album);
        return "admin/addSongForm";
    }


}
