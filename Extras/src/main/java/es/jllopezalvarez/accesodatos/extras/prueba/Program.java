package es.jllopezalvarez.accesodatos.extras.prueba;

import es.jllopezalvarez.accesodatos.extras.prueba.dataaccess.ProductDataAccess;
import es.jllopezalvarez.accesodatos.extras.prueba.dataaccess.ProductDataAccessImpl;
import es.jllopezalvarez.accesodatos.extras.prueba.dataaccess.ProductDataAccessImplXml;
import es.jllopezalvarez.accesodatos.extras.prueba.entities.Customer;
import es.jllopezalvarez.accesodatos.extras.prueba.services.ProductService;
import es.jllopezalvarez.accesodatos.extras.prueba.services.ProductServiceImpl;

public class Program {

    public static void main(String[] args) {
        mostrarProductos();
    }

    private static void mostrarProductos() {
        ProductDataAccess productDataAcces = new ProductDataAccessImplXml();
        ProductService ps = new ProductServiceImpl(productDataAcces);
        System.out.println(ps.findAll());


        Customer c = new Customer(1,"","");
    }
}
