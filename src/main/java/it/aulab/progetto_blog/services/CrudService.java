package it.aulab.progetto_blog.services;

import java.util.List;

public interface CrudService<ReadDTO, Model, Key> {

    List<ReadDTO> readAll();
    ReadDTO read(Key key);
    ReadDTO create(Model model);
    ReadDTO update(Key key, Model model);
    void delete(Key key);
}
