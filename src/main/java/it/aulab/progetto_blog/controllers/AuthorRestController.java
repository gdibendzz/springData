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

import it.aulab.progetto_blog.dtos.AuthorDTO;
import it.aulab.progetto_blog.models.Author;
import it.aulab.progetto_blog.services.AuthorService;

@RestController
@RequestMapping("/api/authors") //path comune di partenza
public class AuthorRestController {

    @Autowired
    AuthorService authorService;

    /*
        RequestMapping: usata per mappare le richiesta http verso metodi specifici definiti nel controller.Può essere applicata sia a livello di metodo che di classe
        ResponseBody: usata per dire che valore di ritorno di un metodo del Controller deve essere corpo della risposta HTTP
    */
    @GetMapping
    public List<AuthorDTO> getAllAuthors() {
        return authorService.readAll();
    }

    @GetMapping("{id}")
    public AuthorDTO getAuthor(@PathVariable("id") Long id) {
        return authorService.read(id);
    }

    @PostMapping
    public AuthorDTO createAuthor(@RequestBody Author entity) {
        return authorService.create(entity);
    }

    @PutMapping("{id}")
    public AuthorDTO updateAuthor(@PathVariable("id") Long id, @RequestBody Author entity) {
        return authorService.update(id, entity);
    }

    @DeleteMapping("{id}")
    public void deleteAuthor(@PathVariable("id") Long id) {
        authorService.delete(id);
    }

}
