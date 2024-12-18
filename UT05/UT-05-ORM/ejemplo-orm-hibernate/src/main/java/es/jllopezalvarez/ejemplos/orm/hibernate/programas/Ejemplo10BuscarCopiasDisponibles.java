package es.jllopezalvarez.ejemplos.orm.hibernate.programas;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

public class Ejemplo10BuscarCopiasDisponibles {
    private static final String QUERY_DISPONIBLES = """
            select i.inventory_id
            from inventory i
            where i.film_id = :film_id
              and i.store_id = :store_id
              and not exists(select rental.inventory_id
                             from rental
                             where rental.inventory_id = i.inventory_id and rental.return_date is null);
            """;


    public static void main(String[] args) {

        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("sakila");
             EntityManager em = emf.createEntityManager()) {

            Query query =  em.createNativeQuery(QUERY_DISPONIBLES, Integer.class);
            query.setParameter("film_id", 49);
            query.setParameter("store_id", 1);

            List<Integer> idsDisponibles = (List<Integer>) query.getResultList();
            System.out.println("Disponibles: " +  idsDisponibles);

        }
    }


}
