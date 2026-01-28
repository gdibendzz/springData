package it.aulab.progetto_blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import it.aulab.progetto_blog.models.Author;
import it.aulab.progetto_blog.models.Post;
import it.aulab.progetto_blog.repositories.AuthorRepository;

@RestController
@RequestMapping("/authors") //path comune di partenza
public class AuthorController {

    @Autowired
    AuthorRepository authorRepository;

    /*
        RequestMapping: usata per mappare le richiesta http verso metodi specifici definiti nel controller.Può essere applicata sia a livello di metodo che di classe
        ResponseBody: usata per dire che valore di ritorno di un metodo del Controller deve essere corpo della risposta HTTP
    */
    @GetMapping
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @GetMapping("{id}")
    public Author getAuthor(@PathVariable("id") Long id) {
        return authorRepository.findById(id).get();
    }

    @PostMapping
    public Author createAuthor(@RequestBody Author entity) {
        return authorRepository.save(entity);
    }

    @PutMapping("{id}")
    public Author updateAuthor(@PathVariable("id") Long id, @RequestBody Author entity) {
        entity.setId(id);
        return authorRepository.save(entity);
    }

    @DeleteMapping("{id}")
    public void deleteAuthor(@PathVariable("id") Long id) {
        if(authorRepository.existsById(id)) {
            Author a = authorRepository.findById(id).get();
            List<Post> authorPosts = a.getPosts();
            for (Post post : authorPosts) {
                post.setAuthor(null);
            }
            authorRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found");
        }
    }

}
