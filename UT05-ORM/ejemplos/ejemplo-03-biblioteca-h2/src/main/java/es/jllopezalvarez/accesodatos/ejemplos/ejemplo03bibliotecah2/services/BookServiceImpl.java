package es.jllopezalvarez.accesodatos.ejemplos.ejemplo03bibliotecah2.services;

import es.jllopezalvarez.accesodatos.ejemplos.ejemplo03bibliotecah2.entities.Book;
import es.jllopezalvarez.accesodatos.ejemplos.ejemplo03bibliotecah2.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

}
