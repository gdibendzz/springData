package it.aulab.progetto_blog.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import it.aulab.progetto_blog.dtos.AuthorDTO;
import it.aulab.progetto_blog.models.Author;
import it.aulab.progetto_blog.models.Post;
import it.aulab.progetto_blog.repositories.AuthorRepository;

@Service("authorService")
public class AuthorService implements CrudService<AuthorDTO, Author, Long> {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<AuthorDTO> readAll() {
        List<AuthorDTO> dtos = new ArrayList<>();
        for(Author author : authorRepository.findAll())
            dtos.add(mapper.map(author, AuthorDTO.class));
        return dtos;
    }

    @Override
    public AuthorDTO read(Long id) {
        Optional<Author> optAuthor = authorRepository.findById(id);
        if(optAuthor.isPresent())
            return mapper.map(optAuthor.get(), AuthorDTO.class);
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Author id=" + id + " not found");
    }

    @Override
    public AuthorDTO create(Author entity) {
        if(entity.getEmail() == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        return mapper.map(authorRepository.save(entity), AuthorDTO.class);
    }

    @Override
    public AuthorDTO update(Long id, Author entity) {
        if(authorRepository.existsById(id)) {
            entity.setId(id);
            return mapper.map(authorRepository.save(entity), AuthorDTO.class);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public void delete(Long id) {
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
