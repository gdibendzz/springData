package it.aulab.progetto_blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.aulab.progetto_blog.dtos.PostDTO;
import it.aulab.progetto_blog.models.Post;
import it.aulab.progetto_blog.services.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostRestController {

    @Autowired
    PostService postService;

    @GetMapping
    public List<PostDTO> getAllPosts() {
        return postService.readAll();
    }

    @GetMapping("{id}")
    public PostDTO getPost(@PathVariable("id") Long id) {
        return postService.read(id);
    }

    @PostMapping
    public PostDTO createPost(@RequestBody Post entity) {
        return postService.create(entity);
    }

    @PutMapping("{id}")
    public PostDTO updatePost(@PathVariable("id") Long id, @RequestBody Post entity) {
        return postService.update(id, entity);
    }

    @DeleteMapping("{id}")
    public void deletePost(@PathVariable("id") Long id) {
        postService.delete(id);
    }
}
