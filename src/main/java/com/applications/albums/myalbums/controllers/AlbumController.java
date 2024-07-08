package com.applications.albums.myalbums.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.applications.albums.myalbums.entities.Album;
import com.applications.albums.myalbums.entities.Comment;
import com.applications.albums.myalbums.entities.User;
import com.applications.albums.myalbums.security.service.UserService;
import com.applications.albums.myalbums.service.IAlbumsService;
import com.applications.albums.myalbums.service.ICommentService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/albums")
@SessionAttributes("comment")
public class AlbumController {

    @Autowired
    private IAlbumsService albumsService;

    @Autowired
    private UserService userService;

    @Autowired
    private ICommentService commentService;


    @GetMapping("/see-comments/{id}")
    public String album(@PathVariable Long id, Model model) {

        Album album = albumsService.albumById(id);
        model.addAttribute("album", album);
        return "comments/seeComments";
    }

    @GetMapping("/load-album-for-comment/{id}")
    public String albumForComment(@PathVariable Long id, Model model){

        Album album = albumsService.albumById(id);

        Comment comment = new Comment();
        comment.setAlbum(album);

        model.addAttribute("comment", comment);
        model.addAttribute("album", album);
        return"comments/commentForm";
    }

    @PostMapping("/save-comment")
    public String saveComment(Comment comment, Authentication auth, HttpSession session, RedirectAttributes redirect){
        String username = auth.getName();
        Optional<User> user = userService.getByUsername(username);
        comment.setUser(user.get());

        commentService.saveComment(comment);

        redirect.addFlashAttribute("savedComment", "Comment saved successfully!");
        return "redirect:/";
    }
}
