package es.jllopezalvarez.ejemplos.orm.hibernate.programas;

import es.jllopezalvarez.ejemplos.orm.hibernate.entities.Category;
import jakarta.persistence.*;

public class Ejemplo02Insertar {

    private static final String SAKILA_PERSISTENCE_UNIT = "sakila";

    public static void main(String[] args) {
        // Obtenemos una factoría de entityManagers para la unidad de persistencia de la BD Sakila
        // Con la factoría, obtenemos el EntityManager.
        // Usamos try-with-resources para que se cierren automáticamente.
        try (EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(SAKILA_PERSISTENCE_UNIT); EntityManager entityManager = entityManagerFactory.createEntityManager()) {

            // En todos los casos, dentro de los métodos de ejemplo, usaremos una transacción. Las transacciones
            // "manuales" son obligatorias para hacer actualizaciones (insert/update/delete) cuando hemos obtenido
            // el entityManager de forma manual (no inyectado) (application-managed entity manager)
            // Cuando se usa inyección de dependencias, se puede usar el atributo @Transactional

            // Opción 1: usando el método persist de EntityManager
            usandoPersist(entityManager);

            // No se puede hacer inserciones con JPQL. Se considera que no tiene mucho sentido, y que
            // se debe usar el método persist.

            // Opción 2: Usando SQL nativo
            usandoSqlNativo(entityManager);
        }

    }


    private static void usandoPersist(EntityManager entityManager) {
        System.out.println("Insertamos una nueva categoría con método persist.");

        // Usamos el método persist() de EntityManager. Se encarga de generar el SQL automáticamente.
        // No hace falta usar JPQL ni SQL.

        // Obtenemos transacción
        EntityTransaction transaction = entityManager.getTransaction();

        // Hacemos la operación dentro de try/catch para poder hacer rollback si algo va mal
        try {
            // Iniciamos transacción
            transaction.begin();

            // Creamos un objeto de la clase "Category".
            // Damos valor solo al campo que la BD no genera automáticamente.
            Category category = new Category();
            category.setName("Nueva categoría");

            // Guardamos el objeto con el método persist
            entityManager.persist(category);

            // Mostramos el objeto en consola para que se vea que ha obtenido un nuevo ID
            System.out.println(category);

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


    private static void usandoSqlNativo(EntityManager entityManager) {
        System.out.println("Insertamos una nueva categoría con SQL nativo.");

        // Obtenemos transacción
        EntityTransaction transaction = entityManager.getTransaction();

        // Hacemos la operación dentro de try/catch para poder hacer rollback si algo va mal
        try {
            // Iniciamos transacción
            transaction.begin();

            // Creamos un objeto Query, con la sentencia insert con parámetros
            Query insertQuery = entityManager.createNativeQuery("insert into category(name) values (:name)");
            insertQuery.setParameter("name", "Nueva categoría");
            insertQuery.executeUpdate();

            // Así no podremos saber el id generado. En otro ejemplo se ve cómo recuperar el id.

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
