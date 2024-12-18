package es.jllopezalvarez.accesodatos.ejemplos.ejemplo03bibliotecah2.controllers;

import es.jllopezalvarez.accesodatos.ejemplos.ejemplo03bibliotecah2.entities.Book;
import es.jllopezalvarez.accesodatos.ejemplos.ejemplo03bibliotecah2.services.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {


    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // Esto responde a /books/hello-world
    @RequestMapping("/hello-world")
    public String metodo() {
        return "Hello World";
    }

    @GetMapping("")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.findAll();
        return ResponseEntity.ok(books);
    }

    @GetMapping(value = "/random-book")
    public ResponseEntity<Book> getRandomBookGetOtraVez() {
        List<Book> allBooks = bookService.findAll();
        int randomIndex = (int) (Math.random() * allBooks.size());
        return ResponseEntity.ok().body(allBooks.get(randomIndex));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookService.findById(id);
        if (book.isPresent()) {
            return ResponseEntity.ok(book.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
}
