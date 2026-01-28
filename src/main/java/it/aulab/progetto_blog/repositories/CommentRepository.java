package it.aulab.progetto_blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import it.aulab.progetto_blog.models.Comment;

public interface CommentRepository extends ListCrudRepository<Comment, Long> {

    List<Comment> findByEmail(String email);
    List<Comment> findByBody(String body);
    List<Comment> findByDate(String date);
    List<Comment> findByEmailAndDate(String email, String date);

    @Query(value = "SELECT * FROM comments c WHERE c.email = 'sium@libero.it'", nativeQuery = true)
    List<Comment> commentsWithSameEmail();

    @Query(value = "SELECT c FROM Comment c WHERE c.email = 'sium@libero.it'")
    List<Comment> commentsWithSameEmailNonNative();
}
