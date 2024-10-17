package es.jllopezalvarez.accesodatos.extras.prueba.services;

import es.jllopezalvarez.accesodatos.extras.prueba.dataaccess.ProductDataAccess;
import es.jllopezalvarez.accesodatos.extras.prueba.entities.Product;

import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {

    private final ProductDataAccess productDataAccess;

    public ProductServiceImpl(ProductDataAccess productDataAccess) {
        this.productDataAccess = productDataAccess;
    }

    @Override
    public Optional<Product> findById(long id) {
        return productDataAccess.findById(id);
    }

    @Override
    public List<Product> findAll() {
        return productDataAccess.findAll();
    }
}
