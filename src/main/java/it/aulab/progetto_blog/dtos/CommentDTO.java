package it.aulab.progetto_blog.dtos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import it.aulab.progetto_blog.models.Post;

public class CommentDTO {

    private Long id;
    private String email, body, date;
    private Post post;
    
    public CommentDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getDateFormatted() {
        if (date == null || date.isBlank()) return "";
        String raw = date.trim();
        if (raw.isEmpty()) return "";

        try {
            LocalDate d = LocalDate.parse(raw, DateTimeFormatter.BASIC_ISO_DATE); // yyyyMMdd
            return d.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (DateTimeParseException e) {
            return raw;
        }
    }
}
