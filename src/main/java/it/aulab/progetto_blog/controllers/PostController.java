package it.aulab.progetto_blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.aulab.progetto_blog.dtos.AuthorDTO;
import it.aulab.progetto_blog.dtos.PostDTO;
import it.aulab.progetto_blog.models.Author;
import it.aulab.progetto_blog.models.Post;
import it.aulab.progetto_blog.services.CrudService;

@Controller
@RequestMapping("/posts")
public class PostController {

    @Autowired
    @Qualifier("postService")
    CrudService<PostDTO, Post, Long> postService;

    @Autowired
    @Qualifier("authorService")
    CrudService<AuthorDTO, Author, Long> authorService;

    @GetMapping
    public String index(Model viewModel) {
        viewModel.addAttribute("title", "Posts");
        viewModel.addAttribute("posts", postService.readAll());
        return "posts";
    }

    @GetMapping("create")
    public String create(Model viewModel) {
        viewModel.addAttribute("title", "Create Post");
        viewModel.addAttribute("post", new Post());
        viewModel.addAttribute("authors", authorService.readAll());
        return "createPost";
    }

    @PostMapping
    public String createPost(@ModelAttribute("post") Post entity) {
        postService.create(entity);
        return "redirect:/posts";
    }
}
