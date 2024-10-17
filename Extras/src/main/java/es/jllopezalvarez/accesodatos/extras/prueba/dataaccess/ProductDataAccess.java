package es.jllopezalvarez.accesodatos.extras.prueba.dataaccess;

import es.jllopezalvarez.accesodatos.extras.prueba.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDataAccess {
    Optional<Product> findById(long id);
    List<Product> findAll();
    Product save(Product product);
}
