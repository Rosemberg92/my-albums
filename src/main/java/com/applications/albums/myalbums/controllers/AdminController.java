package com.applications.albums.myalbums.controllers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.applications.albums.myalbums.entities.Album;
import com.applications.albums.myalbums.entities.Song;
import com.applications.albums.myalbums.service.IAlbumsService;
import com.applications.albums.myalbums.service.ISongService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IAlbumsService albumsService;

    @Autowired
    private ISongService songService;

    // ! Método para mostrar el formulario de creación de un álbum
    @GetMapping("/album-form")
    public String albumFrom(Model model) {
        model.addAttribute("album", new Album());
        return "admin/albumForm";
    }

    // ! Método para guardar un álbum
    @PostMapping("/save-album")
    public String saveAlbum(@RequestParam(name = "file", required = false) MultipartFile folder, Album album,
            RedirectAttributes redirect) {

        if (!folder.isEmpty()) {

            String route = "C://Temp//uploads";
            String uniqName = UUID.randomUUID() + " " + folder.getOriginalFilename();

            try {
                byte[] bytes = folder.getBytes();
                Path absolutePath = Paths.get(route + "//" + uniqName);
                Files.write(absolutePath, bytes);
                album.setFolder(uniqName);
                albumsService.save(album);
                redirect.addFlashAttribute("success", "Album guardado correctamente");
                redirect.addFlashAttribute("songForAlbum", album);
            } catch (Exception e) {
                e.getCause().getMessage();
            }
        }
        return "redirect:/songs/songs-form";
    }

    @GetMapping("/edit-album")
    public String albumListFunc(Model model) {

        model.addAttribute("albums", albumsService.listAlbums());

        return "admin/editAlbum";
    }

    //! Método para eliminar un álbum
    @GetMapping("/delete-album/{id}")
    public String deleteAlbum(@PathVariable Long id, RedirectAttributes redirect) {
        albumsService.deleteAlbum(id);
        redirect.addFlashAttribute("albumDeleted", "Album deleted successfully");
        return "redirect:/admin/edit-album";
    }

    @GetMapping("/edit-form/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Album album = null;

        if (id > 0) {
            album = albumsService.albumById(id);
            model.addAttribute("album", album);
        }
        return "admin/editForm";
    }

    //! Método para editar un álbum
    @PostMapping("/edit-album")
    public String editAlbum(@RequestParam(name = "file") MultipartFile folder, Album alb, RedirectAttributes redirect,
            @ModelAttribute("album") Album album, Model model) {

        if (!folder.isEmpty()) {
            String route = "C://Temp//uploads";
            String uniqName = UUID.randomUUID() + " " + folder.getOriginalFilename();

            try {
                byte[] bytes = folder.getBytes();
                Path absolutePath = Paths.get(route + "//" + uniqName);
                Files.write(absolutePath, bytes);
                album.setFolder(uniqName);

                albumsService.save(album);
                redirect.addFlashAttribute("albumEdit", "Album edited successfully");

            } catch (Exception e) {
                e.getCause().getMessage();
            }
        }

        return "redirect:/admin/edit-album";
    }

    //! Método para editar las canciones de un álbum
    @GetMapping("/edit-songs/{id}")
    public String editSongs(@PathVariable Long id, Model model){

        Album albumById = albumsService.albumById(id);
        model.addAttribute("albumFinded", albumById);
        return "admin/editSongs";
    }

    @GetMapping("/edit-song/{id}")
    public String loadSong(@PathVariable Long id, Model model){
        Song song = songService.findSongById(id);
        model.addAttribute("song", song);
        return "admin/editSongForm";
    }

    @PostMapping("/mod-song")
    public String editSong(@ModelAttribute("song") Song song, RedirectAttributes redirect){
        songService.saveSong(song);
        redirect.addFlashAttribute("songMod", "Song edited successfully");


        return "redirect:/admin/edit-album";
    }

    @GetMapping("/delete-song/{id}")
    public String deleteSong(@PathVariable Long id, RedirectAttributes redirect){
    songService.deleteSong(id);
    redirect.addFlashAttribute("songDeleted", "Song deleted successfully");
    return "redirect:/admin/edit-album";
    }

    @PostMapping("/save-song")
    public String saveSong(Song song, RedirectAttributes redirect, Model model){
        songService.saveSong(song);
        redirect.addFlashAttribute("savedSong", "Song saved successfully!");
        return "redirect:/admin/edit-album";
    }


}