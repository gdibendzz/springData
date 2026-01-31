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
import it.aulab.progetto_blog.models.Author;
import it.aulab.progetto_blog.services.CrudService;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    @Qualifier("authorService")
    CrudService<AuthorDTO, Author, Long> authorService;

    /*
        Model: oggetto che il template potrà utilizzare e manipolare e useremo soprattutto come pacchetto per passare i dati dal client al server e viceversa
     */
    @GetMapping
    public String index(Model viewModel) {
        viewModel.addAttribute("title", "Authors");
        viewModel.addAttribute("authors", authorService.readAll());
        return "authors";
    }

    @GetMapping("create")
    public String create(Model viewModel) {
        viewModel.addAttribute("title", "Create Author");
        viewModel.addAttribute("author", new Author());
        return "createAuthor";
    }

    @PostMapping
    /*
        ModelAttribute: annotation che indica al metodo che dell'oggetto model proveniente dal form deve catturare l'attributo 'author'
     */
    public String createAuthor(@ModelAttribute("author") Author entity) {
        authorService.create(entity);
        return "redirect:/authors";
    }
}
