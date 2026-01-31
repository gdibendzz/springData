package it.aulab.progetto_blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.aulab.progetto_blog.dtos.CommentDTO;
import it.aulab.progetto_blog.dtos.PostDTO;
import it.aulab.progetto_blog.models.Comment;
import it.aulab.progetto_blog.models.Post;
import it.aulab.progetto_blog.services.CrudService;

@Controller
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    @Qualifier("commentService")
    CrudService<CommentDTO, Comment, Long> commentService;

    @Autowired
    @Qualifier("postService")
    CrudService<PostDTO, Post, Long> postService;

    @GetMapping
    public String index(Model viewModel) {
        viewModel.addAttribute("title", "Comments");
        viewModel.addAttribute("comments", commentService.readAll());
        return "comments";
    }

    @GetMapping("create")
    public String create(Model viewModel) {
        viewModel.addAttribute("title", "Create Comment");
        viewModel.addAttribute("comment", new Comment());
        viewModel.addAttribute("posts", postService.readAll());
        return "createComment";
    }

    @PostMapping
    public String createAuthor(@ModelAttribute("comments") Comment entity) {
        commentService.create(entity);
        return "redirect:/comments";
    }
}
