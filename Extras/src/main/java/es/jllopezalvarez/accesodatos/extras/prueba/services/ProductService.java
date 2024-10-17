package es.jllopezalvarez.accesodatos.extras.prueba.services;

import es.jllopezalvarez.accesodatos.extras.prueba.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<Product> findById(long id);
    List<Product> findAll();
}
