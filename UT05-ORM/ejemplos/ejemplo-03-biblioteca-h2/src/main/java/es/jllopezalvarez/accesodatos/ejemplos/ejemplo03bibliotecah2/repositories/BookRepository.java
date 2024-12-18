package es.jllopezalvarez.accesodatos.ejemplos.ejemplo03bibliotecah2.repositories;

import es.jllopezalvarez.accesodatos.ejemplos.ejemplo03bibliotecah2.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
