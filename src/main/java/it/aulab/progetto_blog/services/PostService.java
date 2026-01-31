package it.aulab.progetto_blog.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import it.aulab.progetto_blog.dtos.PostDTO;
import it.aulab.progetto_blog.models.Comment;
import it.aulab.progetto_blog.models.Post;
import it.aulab.progetto_blog.repositories.PostRepository;

@Service("postService")
public class PostService implements CrudService<PostDTO, Post, Long> {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<PostDTO> readAll() {
        List<PostDTO> dtos = new ArrayList<>();
        for(Post p : postRepository.findAll())
            dtos.add(mapper.map(p, PostDTO.class));
        return dtos;
    }

    @Override
    public PostDTO read(Long id) {
        Optional<Post> optPost = postRepository.findById(id);
        if(optPost.isPresent())
            return mapper.map(optPost.get(), PostDTO.class);
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post id=" + id + " not found");
    }

    @Override
    public PostDTO create(Post entity) {
        if(entity.getTitle() == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        DateTimeFormatter inF = DateTimeFormatter.ISO_LOCAL_DATE;
        DateTimeFormatter dbF = DateTimeFormatter.BASIC_ISO_DATE;
        String dbValue = (entity.getPublishDate() != null) ? LocalDate.parse(entity.getPublishDate(), inF).format(dbF) : "";
        entity.setPublishDate(dbValue);
        return mapper.map(postRepository.save(entity), PostDTO.class);
    }

    @Override
    public PostDTO update(Long id, Post entity) {
        if(postRepository.existsById(id)) {
            entity.setId(id);
            return mapper.map(postRepository.save(entity), PostDTO.class);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public void delete(Long id) {
        if(postRepository.existsById(id)) {
            Post p = postRepository.findById(id).get();
            List<Comment> commentPosts = p.getComments();
            for (Comment comment : commentPosts) {
                comment.setPost(null);
            }
            postRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found");
        }
    }
}
