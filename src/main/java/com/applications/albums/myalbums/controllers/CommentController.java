package com.applications.albums.myalbums.controllers;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.applications.albums.myalbums.entities.Comment;
import com.applications.albums.myalbums.entities.User;
import com.applications.albums.myalbums.security.service.UserService;
import com.applications.albums.myalbums.service.ICommentService;

import jakarta.servlet.http.HttpSession;



@Controller
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private ICommentService commentService;

    @Autowired
    private UserService userService;

    //! Método para cargar el formulario de comentarios
    @GetMapping("/edit-comment/{id}")
    public String editCommentForm(@PathVariable Long id, Model model) {

        model.addAttribute("comment", commentService.getCommentById(id));

        return "comments/editCommentForm";
    }

    //! Método para editar un comentario
    @GetMapping("/edit/")
    public String edit(@ModelAttribute("comment")Comment comment, RedirectAttributes redirectAttributes,
    Authentication authentication, HttpSession session) {
        String username = authentication.getName();

        Optional<User> user = userService.getByUsername(username);

        comment.setUser(user.get());
        commentService.saveComment(comment);

        redirectAttributes.addFlashAttribute("commentEdited", "Comment edited successfully");
        return "redirect:/";
    }


    //! Método para eliminar un comentario
    @GetMapping("/delete-comment/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        commentService.deleteComment(id);
        redirectAttributes.addFlashAttribute("commentDeleted", "Comment deleted successfully");
        return "redirect:/";

    }
}
