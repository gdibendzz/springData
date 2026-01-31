package it.aulab.progetto_blog.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import it.aulab.progetto_blog.dtos.CommentDTO;
import it.aulab.progetto_blog.models.Comment;
import it.aulab.progetto_blog.repositories.CommentRepository;

@Service("commentService")
public class CommentService implements CrudService<CommentDTO, Comment, Long> {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<CommentDTO> readAll() {
        List<CommentDTO> dtos = new ArrayList<>();
        for(Comment c : commentRepository.findAll())
            dtos.add(mapper.map(c, CommentDTO.class));
        return dtos;
    }

    @Override
    public CommentDTO read(Long id) {
        Optional<Comment> optComment = commentRepository.findById(id);
        if(optComment.isPresent())
            return mapper.map(optComment.get(), CommentDTO.class);
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Author id=" + id + " not found");
    }

    @Override
    public CommentDTO create(Comment entity) {
        return mapper.map(commentRepository.save(entity), CommentDTO.class);
    }

    @Override
    public CommentDTO update(Long id, Comment entity) {
        if(commentRepository.existsById(id)) {
            entity.setId(id);
            return mapper.map(commentRepository.save(entity), CommentDTO.class);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public void delete(Long id) {
        if(commentRepository.existsById(id))
            commentRepository.deleteById(id);
        else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }    
}
