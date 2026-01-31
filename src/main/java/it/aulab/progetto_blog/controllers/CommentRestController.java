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

import it.aulab.progetto_blog.dtos.CommentDTO;
import it.aulab.progetto_blog.models.Comment;
import it.aulab.progetto_blog.services.CommentService;

@RestController
@RequestMapping("/api/comments")
public class CommentRestController {

    @Autowired
    CommentService commentService;

    @GetMapping
    public List<CommentDTO> getAllComments() {
        return commentService.readAll();
    }

    @GetMapping("{id}")
    public CommentDTO getComment(@PathVariable("id") Long id) {
        return commentService.read(id);
    }

    @PostMapping
    public CommentDTO createComment(@RequestBody Comment entity) {
        return commentService.create(entity);
    }

    @PutMapping("{id}")
    public CommentDTO updateComment(@PathVariable("id") Long id, @RequestBody Comment entity) {
        return commentService.update(id, entity);
    }

    @DeleteMapping("{id}")
    public void deleteComment(@PathVariable("id") Long id) {
        commentService.delete(id);
    }
}
