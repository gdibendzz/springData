package it.aulab.progetto_blog.dtos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import it.aulab.progetto_blog.models.Author;

public class PostDTO {
    private Long id;
    private String title, body, publishDate;
    private Author author;

    public PostDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getPublishDateFormatted() {
        if (publishDate == null || publishDate.isBlank()) return "";
        String raw = publishDate.trim();
        if (raw.isEmpty()) return "";

        try {
            LocalDate d = LocalDate.parse(raw, DateTimeFormatter.BASIC_ISO_DATE); // yyyyMMdd
            return d.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (DateTimeParseException e) {
            return raw;
        }
    }
}
