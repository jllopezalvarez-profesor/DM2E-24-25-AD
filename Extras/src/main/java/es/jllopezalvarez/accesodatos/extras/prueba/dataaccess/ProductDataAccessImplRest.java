package es.jllopezalvarez.accesodatos.extras.prueba.dataaccess;

import es.jllopezalvarez.accesodatos.extras.prueba.entities.Product;

import java.util.List;
import java.util.Optional;

public class ProductDataAccessImplRest implements ProductDataAccess {
    @Override
    public Optional<Product> findById(long id) {
        return Optional.empty();
    }

    @Override
    public List<Product> findAll() {
        return List.of();
    }

    @Override
    public Product save(Product product) {
        return null;
    }
}
