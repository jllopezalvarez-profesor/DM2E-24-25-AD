package es.jllopezalvarez.ejemplos.orm.hibernate.programas;

import es.jllopezalvarez.ejemplos.orm.hibernate.entities.City;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Scanner;


public class Ejemplo07ManyToOne {

    private static final String SAKILA_PERSISTENCE_UNIT = "sakila";

    // Scanner para preguntar al usuario datos para pruebas
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Obtenemos una factoría de entityManagers para la unidad de persistencia de la BD Sakila
        // Con la factoría, obtenemos el EntityManager.
        // Usamos try-with-resources para que se cierren automáticamente.
        try (EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(SAKILA_PERSISTENCE_UNIT); EntityManager entityManager = entityManagerFactory.createEntityManager()) {

            // Preguntamos al usuario el id de una ciudad.
            System.out.print("Por favor introduce el ID de una ciudad: ");
            int cityId = Integer.parseInt(scanner.nextLine());

            // Mostrar país y sus ciudades:
            showCityAndCountry(cityId, entityManager);
        }



    }


    private static void showCityAndCountry(int cityId, EntityManager entityManager) {
        // Buscamos con find
        City city = entityManager.find(City.class, cityId);

        // Si no encontramos el país mostramos error
        if (city == null) {
            System.out.printf("No se ha encontrado la ciudad con id %d\n", cityId);
            return;
        }

        // Mostramos datos del país
        System.out.printf("Ciudad: %d / %s - Actualizado: %s\n", city.getCityId(), city.getName(), city.getLastUpdate());
        // Hacemos pausa par que veamos que no se ha ejecutado la consulta de BBDD (sólo si hemos cambiado para que haga lazy loading al declarar la relación @ManyToOne)
        System.out.println("Pulsa intro para ver el país de la ciudad");
        scanner.nextLine();
        // Mostramos país
        System.out.printf("\tPaís: %d / %s - Actualizado: %s\n", city.getCountry().getCountryId(), city.getCountry().getName(), city.getCountry().getLastUpdate());
        // Hacemos segunda pausa par que veamos que no se vuelve a consultar a la BBDD
        System.out.println("Pulsa intro para ver de nuevo el país de la ciudad");
        scanner.nextLine();
        // Mostramos ciudades
        System.out.printf("\tPaís: %d / %s - Actualizado: %s\n", city.getCountry().getCountryId(), city.getCountry().getName(), city.getCountry().getLastUpdate());

        System.out.println();
    }

}
