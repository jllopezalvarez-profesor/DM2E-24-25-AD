package es.jllopezalvarez.accesodatos.ejemplos.ejemplo03bibliotecah2.services;

import es.jllopezalvarez.accesodatos.ejemplos.ejemplo03bibliotecah2.entities.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();

    Optional<Book> findById(Long id);
}
