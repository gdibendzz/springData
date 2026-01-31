package it.aulab.progetto_blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import it.aulab.progetto_blog.models.Author;

public interface AuthorRepository extends ListCrudRepository<Author, Long> {

    List<Author> findByFirstname(String firstname);
    List<Author> findByLastname(String lastname);
    List<Author> findByFirstnameAndLastname(String firstname, String lastname);
    List<Author> findByEmail(String email);

    //Per creare query personalizzate(in questo caso è nativa)
    @Query(value = "SELECT * FROM authors a WHERE a.firstname = 'Giuseppe'", nativeQuery = true)
    List<Author> authorsWithSameFirstname();

    @Query(value = "SELECT a FROM Author a WHERE a.firstname = 'Giuseppe'")
    List<Author> authorsWithSameFirstnameNonNative();
}