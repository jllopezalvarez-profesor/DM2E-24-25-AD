package es.jllopezalvarez.ejemplos.orm.hibernate.programas;

import es.jllopezalvarez.ejemplos.orm.hibernate.entities.City;
import es.jllopezalvarez.ejemplos.orm.hibernate.entities.Country;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Scanner;


public class Ejemplo06OneToMany {

    private static final String SAKILA_PERSISTENCE_UNIT = "sakila";

    // Scanner para preguntar al usuario datos para pruebas
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Obtenemos una factoría de entityManagers para la unidad de persistencia de la BD Sakila
        // Con la factoría, obtenemos el EntityManager.
        // Usamos try-with-resources para que se cierren automáticamente.
        try (EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(SAKILA_PERSISTENCE_UNIT); EntityManager entityManager = entityManagerFactory.createEntityManager()) {

            // Preguntamos al usuario el id de un país.
            System.out.print("Por favor introduce el ID de un país: ");
            int countryId = Integer.parseInt(scanner.nextLine());

            // Mostrar país y sus ciudades:
            showCountryAndCities(countryId, entityManager);
        }


    }


    private static void showCountryAndCities(int countryId, EntityManager entityManager) {
        // Buscamos con find
        Country country = entityManager.find(Country.class, countryId);

        // Si no encontramos el país mostramos error
        if (country == null) {
            System.out.printf("No se ha encontrado el país  con id %d\n", countryId);
            return;
        }

        // Mostramos datos del país
        System.out.printf("País: %d / %s - Actualizado: %s\n", country.getCountryId(), country.getName(), country.getLastUpdate());
        // Hacemos pausa par que veamos que no se ha ejecutado la consulta de BBDD
        System.out.println("Pulsa intro para ver las ciudades del país");
        scanner.nextLine();
        // Mostramos ciudades
        showCountryCities(country);
        // Hacemos segunda pausa par que veamos que no se vuelve a consultar a la BBDD
        System.out.println("Pulsa intro para ver de nuevo las ciudades del país");
        scanner.nextLine();
        // Mostramos ciudades
        showCountryCities(country);

        System.out.println();
    }

    private static void showCountryCities(Country country) {
        System.out.println("Ciudades:");
        for (City city : country.getCities()) {
            System.out.printf("\t%d / %s %s\n", city.getCityId(), city.getName(), city.getLastUpdate());
        }
    }
}
