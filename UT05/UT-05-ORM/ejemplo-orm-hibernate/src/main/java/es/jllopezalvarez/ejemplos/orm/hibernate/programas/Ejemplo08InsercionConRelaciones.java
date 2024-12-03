package es.jllopezalvarez.ejemplos.orm.hibernate.programas;

import es.jllopezalvarez.ejemplos.orm.hibernate.entities.City;
import es.jllopezalvarez.ejemplos.orm.hibernate.entities.Country;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;


public class Ejemplo08InsercionConRelaciones {

    private static final String SAKILA_PERSISTENCE_UNIT = "sakila";

    public static void main(String[] args) {
        // Obtenemos una factoría de entityManagers para la unidad de persistencia de la BD Sakila
        // Con la factoría, obtenemos el EntityManager.
        // Usamos try-with-resources para que se cierren automáticamente.
        try (EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(SAKILA_PERSISTENCE_UNIT);
             EntityManager entityManager = entityManagerFactory.createEntityManager()) {

            // En estos ejemplos aprenderemos a insertar entidades que tienen una relación con otra entidad.
            // Vamos a probar a insertar ciudades (city), de varias formas. La idea es crear un objeto de la
            // clase "City", y asociarla a un objeto de la clase "Country". Vamos a probar los varios casos,
            // simple usando el método persist() del entityManager, y lo que cambia entre ellos es la forma
            // en que vamos a asociar las entidades, asociar el país a la ciudad. Los casos que vamos a ver son:
            // - 1: Sin asociar entidad de la clase "Country", para ilustrar el error que se produce.
            // - 2: Usando una entidad de la clase "Country" completa, y que exista en la BD.
            // - 3: Usando una referencia de la clase "Country", y que exista en la BD.
            // - 4: Usando una entidad de la clase "Country" completa, pero que no existe aún en la BD.
            // - 5: Usando una referencia de la clase "Country", que no exista en la base de datos.
            // En todos los casos, como estamos actualizando la BD, hay que usar transacción.

            // Caso 1: insertar city sin asociar county
//            insertarCitySinCountry(entityManager);

            // Caso 2: insertar city asociando un county que previamente hemos cargado de la BD
//            insertarCityConCountryCompleto(entityManager);

            // Caso 3: insertar city asociando una referencia a una entidad que existe en la BD
//            insertarCityConReferenciaOk(entityManager);

            // Caso 4: insertar city asociando un county que no existe en la BD
            insertarCityConCountryCompletoNuevo(entityManager);

            // Caso 5: insertar city con una referencia errónea
//            insertarCityConReferenciaError(entityManager);
        }


    }




    private static void insertarCitySinCountry(EntityManager entityManager) {
        // Obtenemos transacción
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            // Iniciamos transacción
            transaction.begin();

            // Creamos objeto City. Solo tenemos que dar valor al nombre de la ciudad. El resto los crea la BD.
            City city = new City();
            city.setName("Nueva ciudad");
            // En este punto deberíamos asociar el país (city.setCountry()), pero no lo hacemos para que falle.

            // Guardamos la ciudad
            entityManager.persist(city);

            // Commit de la transacción
            transaction.commit();
        } finally {
            // Si la transacción sigue abierta al llegar aquí, es que tenemos
            // problemas, y hay que deshacer los cambios
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }

    }

    private static void insertarCityConCountryCompleto(EntityManager entityManager) {
        // Obtenemos transacción
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            // Iniciamos transacción
            transaction.begin();

            // Obtenemos un país al que asociar la ciudad.
            // Esto implica un acceso a la BD
            Country country = entityManager.find(Country.class, 10);

            // Creamos objeto City. Solo tenemos que dar valor al nombre de la ciudad. El resto los crea la BD.
            City city = new City();
            city.setName("Nueva ciudad");
            city.setCountry(country);

            // Guardamos la ciudad
            entityManager.persist(city);

            // Mostramos la ciudad
            System.out.printf("Id de la ciudad insertada: %d\n", city.getCityId());

            // Commit de la transacción
            transaction.commit();
        } finally {
            // Si la transacción sigue abierta al llegar aquí, es que tenemos
            // problemas, y hay que deshacer los cambios
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    private static void insertarCityConReferenciaOk(EntityManager entityManager) {
        // Obtenemos transacción
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            // Iniciamos transacción
            transaction.begin();

            // Obtenemos una referencia al país al que asociar la ciudad.
            // Esto no realiza acceso a la BD a menos que miremos atributos de la referencia distintos al ID
            Country country = entityManager.getReference(Country.class, 10);

            // Creamos objeto City. Solo tenemos que dar valor al nombre de la ciudad. El resto los crea la BD.
            City city = new City();
            city.setName("Nueva ciudad");
            city.setCountry(country);

            // Guardamos la ciudad
            entityManager.persist(city);

            // Mostramos la ciudad
            System.out.printf("Id de la ciudad insertada: %d\n", city.getCityId());

            // Commit de la transacción
            transaction.commit();
        } finally {
            // Si la transacción sigue abierta al llegar aquí, es que tenemos
            // problemas, y hay que deshacer los cambios
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    private static void insertarCityConCountryCompletoNuevo(EntityManager entityManager) {
        // Obtenemos transacción
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            // Iniciamos transacción
            transaction.begin();

            // Creamos un nuevo objeto Country y le damos valores a los campos que no se autocompletan en la BD
            Country country = new Country();
            country.setName("Nuevo país con ciudad");

            // Tenemos que guardar este país antes de asociarlo, porque si no, fallará
            entityManager.persist(country);

            // Creamos objeto City. Solo tenemos que dar valor al nombre de la ciudad. El resto los crea la BD.
            City city = new City();
            city.setName("Nueva ciudad con país");
            city.setCountry(country);

            // Guardamos la ciudad
            entityManager.persist(city);

            // Mostramos la ciudad
            System.out.printf("Id de la ciudad insertada: %d\n", city.getCityId());

            // Commit de la transacción
            transaction.commit();
        } finally {
            // Si la transacción sigue abierta al llegar aquí, es que tenemos
            // problemas, y hay que deshacer los cambios
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    private static void insertarCityConReferenciaError(EntityManager entityManager) {
        // Obtenemos transacción
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            // Iniciamos transacción
            transaction.begin();

            // Obtenemos una referencia al país al que asociar la ciudad.
            // Vamos a usar un ID de país que no existe, para así provocar un error
            // Esto no realiza acceso a la BD
            Country country = entityManager.getReference(Country.class, 125);

            // Creamos objeto City. Solo tenemos que dar valor al nombre de la ciudad. El resto los crea la BD.
            City city = new City();
            city.setName("Nueva ciudad");
            city.setCountry(country);

            // Guardamos la ciudad
            entityManager.persist(city);

            // Mostramos la ciudad
            System.out.printf("Id de la ciudad insertada: %d\n", city.getCityId());

            // Commit de la transacción
            transaction.commit();
        } finally {
            // Si la transacción sigue abierta al llegar aquí, es que tenemos
            // problemas, y hay que deshacer los cambios
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }
}
