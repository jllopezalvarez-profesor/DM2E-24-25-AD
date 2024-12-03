package es.jllopezalvarez.ejemplos.orm.hibernate.programas;

import es.jllopezalvarez.ejemplos.orm.hibernate.entities.Actor;
import es.jllopezalvarez.ejemplos.orm.hibernate.entities.Film;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Scanner;


public class Ejemplo05ManyToMany {

    private static final String SAKILA_PERSISTENCE_UNIT = "sakila";

    // Scanner para preguntar al usuario datos para pruebas
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Obtenemos una factoría de entityManagers para la unidad de persistencia de la BD Sakila
        // Con la factoría, obtenemos el EntityManager.
        // Usamos try-with-resources para que se cierren automáticamente.
        try (EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(SAKILA_PERSISTENCE_UNIT); EntityManager entityManager = entityManagerFactory.createEntityManager()) {

            // Preguntamos al usuario el id de una película.
            System.out.print("Por favor introduce el ID de una película: ");
            int filmId = Integer.parseInt(scanner.nextLine());

            // Mostrar película y sus actores:
            showFilmAndActors(filmId, entityManager);

            // Preguntamos al usuario el id de un actor
            System.out.print("Por favor introduce el ID de un actor o actriz: ");
            int actorId = Integer.parseInt(scanner.nextLine());

            // Mostrar datos del actor/actriz y sus películas
            showActorAndFilms(actorId, entityManager);
        }

    }


    private static void showFilmAndActors(int filmId, EntityManager entityManager) {
        // Buscamos con find
        Film film = entityManager.find(Film.class, filmId);

        // Si no encontramos la película mostramos error
        if (film == null) {
            System.out.printf("No se ha encontrado la película con id %d\n", filmId);
            return;
        }

        // Mostramos datos de la película
        System.out.printf("Película: %d / %s - Año: %s\n", film.getFilmId(), film.getTitle(), film.getReleaseYear());
        // Hacemos pausa par que veamos que no se ha ejecutado la consulta de BBDD
        System.out.println("Pulsa intro para ver los actores de la película");
        scanner.nextLine();
        // Mostramos actores
        showFilmActors(film);
        // Hacemos segunda pausa par que veamos que no se vuelve a consultar a la BBDD
        System.out.println("Pulsa intro para ver de nuevo los actores de la película");
        scanner.nextLine();
        // Mostramos actores
        showFilmActors(film);

        System.out.println();
    }

    private static void showFilmActors(Film film) {
        System.out.println("Actores:");
        for (Actor actor : film.getActors()) {
            System.out.printf("\t%d / %s %s\n", actor.getActorId(), actor.getFirstName(), actor.getLastName());
        }
    }

    private static void showActorAndFilms(int actorId, EntityManager entityManager) {
        // Buscamos con find
        Actor actor = entityManager.find(Actor.class, actorId);

        // Si no encontramos la película mostramos error
        if (actor == null) {
            System.out.printf("No se ha encontrado el actor con id %d\n", actorId);
            return;
        }

        // Mostramos datos del actor
        System.out.printf("Actor: %d / %s %s\n", actor.getActorId(), actor.getFirstName(), actor.getLastName());
        // Hacemos pausa par que veamos que no se ha ejecutado la consulta de BBDD
        System.out.println("Pulsa intro para ver las películas del actor");
        scanner.nextLine();
        // Mostramos películas
        System.out.println("Películas:");
        for (Film film : actor.getFilms()) {
            System.out.printf("%d / %s - Año: %s\n", film.getFilmId(), film.getTitle(), film.getReleaseYear());
        }
        System.out.println();
    }
}
