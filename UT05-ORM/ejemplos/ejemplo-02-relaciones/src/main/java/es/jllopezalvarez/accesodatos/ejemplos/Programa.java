package es.jllopezalvarez.accesodatos.ejemplos;

import es.jllopezalvarez.accesodatos.ejemplos.ejemplo02relaciones.entities.City;
import es.jllopezalvarez.accesodatos.ejemplos.ejemplo02relaciones.entities.Country;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class Programa {


    public static void main(String[] args) {
        try (EntityManager manager = Persistence.createEntityManagerFactory("sakila").createEntityManager()) {

            City city = manager.find(City.class, 1L);
            System.out.println(city.getCity());
//            System.out.println(city.getCountry().getCountry());

            Country country = manager.find(Country.class, 4L);
            for(City countryCity : country.getCities()) {
                System.out.println(countryCity.getCity());
            }



        }
    }

}
