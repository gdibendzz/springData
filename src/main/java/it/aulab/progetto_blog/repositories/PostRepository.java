package it.aulab.progetto_blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.aulab.progetto_blog.models.Post;

public interface PostRepository extends CrudRepository<Post, Long> {

    List<Post> findByTitle(String title);
    List<Post> findByBody(String body);
    List<Post> findByPublishDate(String publishDate);
    
    @Query(value = "SELECT * FROM posts p WHERE p.title = 'Test'", nativeQuery = true)
    List<Post> postsWithSameTitle();

    @Query(value = "SELECT p FROM Post p WHERE p.title = 'Test'")
    List<Post> postsWithSameTitleNonNative();
}
