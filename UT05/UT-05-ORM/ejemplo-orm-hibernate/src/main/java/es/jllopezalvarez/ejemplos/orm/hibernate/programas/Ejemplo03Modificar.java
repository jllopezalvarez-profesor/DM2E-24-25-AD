package es.jllopezalvarez.ejemplos.orm.hibernate.programas;

import es.jllopezalvarez.ejemplos.orm.hibernate.entities.Category;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Ejemplo03Modificar {

    private static final String SAKILA_PERSISTENCE_UNIT = "sakila";

    private static final int TEST_CATEGORY_ID = 120;

    public static void main(String[] args) {
        // Obtenemos una factoría de entityManagers para la unidad de persistencia de la BD Sakila
        // Con la factoría, obtenemos el EntityManager.
        // Usamos try-with-resources para que se cierren automáticamente.
        try (EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(SAKILA_PERSISTENCE_UNIT);
             EntityManager entityManager = entityManagerFactory.createEntityManager()) {

            // En todos los casos, dentro de los métodos de ejemplo, usaremos una transacción. Las transacciones
            // "manuales" son obligatorias para hacer actualizaciones (insert/update/delete) cuando hemos obtenido
            // el entityManager de forma manual (no inyectado) (application-managed entity manager)
            // Cuando se usa inyección de dependencias, se puede usar el atributo @Transactional

            // Opción 1: usando el seguimiento de entidades (tracking)
//            usandoTracking(entityManager);

            // Opción 2: Usando una referencia (getReference)
            usandoReferencia(entityManager);

            // Opción 3: usando JPQL
//            usandoJpql(entityManager);

            // Opción 4: Usando SQL nativo
//            usandoSqlNativo(entityManager);
        }

    }


    private static void usandoTracking(EntityManager entityManager) {
        System.out.println("Modificamos una categoría usando el tracking de entidades.");

        // El entity manager mantiene un control sobre todas las entidades que recupera de la base de datos,
        // identificando los cambios que se van produciendo en ellas.
        // Aunque los cambios los hayamos hecho fuera de transacción, cuando hacemos el commit de la transacción
        // se guardarán los cambios.

        // Obtenemos transacción
        EntityTransaction transaction = entityManager.getTransaction();

        // Hacemos la operación dentro de try/catch para poder hacer rollback si algo va mal
        try {
            // Iniciamos transacción
            transaction.begin();

            // Primero recuperamos una categoría con el método find
            Category category = entityManager.find(Category.class, TEST_CATEGORY_ID);

            // Si la categoría es null, es que no existe. Mostramos un mensaje para evitar excepciones
            if (category == null) {
                System.out.printf("No se ha encontrado la categoría con id '%d'\n", TEST_CATEGORY_ID);
                return;
            }

            // Ahora simplemente modificamos la categoría, el nombre
            //String newName = LocalTime.now().toString();
            String newName = LocalTime.now().toString();
//            newName = category.getName();
//            category.setName("Esto es otro valor");
            category.setName(newName);

            // Y completamos la transacción. Esto hace que se guarden los datos.
            transaction.commit();
        } finally {
            // Si la transacción sigue abierta al llegar aquí, es que tenemos
            // problemas, y hay que deshacer los cambios
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    private static void usandoReferencia(EntityManager entityManager) {
        System.out.println("Modificamos una categoría usando una referencia.");

        // En este caso creamos una referencia a una entidad con el método getReference.
        // Recordemos que este método crea una entidad "vacía", con solo la clave.
        // Podemos modificar los atributos que queramos (excepto la clave) y cuando se
        // hace el commit de la transacción se guardarán los cambios.

        // Obtenemos transacción
        EntityTransaction transaction = entityManager.getTransaction();

        // Hacemos la operación dentro de try/catch para poder hacer rollback si algo va mal
        try {
            // Iniciamos transacción
            transaction.begin();

            // Primero recuperamos una creferencia a la ategoría con el método getReference
            Category category = entityManager.getReference(Category.class, TEST_CATEGORY_ID);

            // Si la categoría es null, es que no existe. Mostramos un mensaje para evitar excepciones
            if (category == null) {
                System.out.printf("No se ha encontrado la categoría con id '%d'\n", TEST_CATEGORY_ID);
                return;
            }

            // Ahora simplemente modificamos la categoría, el nombre
            String newName = LocalTime.now().toString();
            category.setName(newName);

            // Y completamos la transacción. Esto hace que se guarden los datos.
            transaction.commit();
        } finally {
            // Si la transacción sigue abierta al llegar aquí, es que tenemos
            // problemas, y hay que deshacer los cambios
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    private static void usandoJpql(EntityManager entityManager) {
        // El lenguaje JPQL es similar al lenguaje SQL, pero permite hacer consultas usando los
        // nombres de clases y nombres de atributos en lugar de los nombres de columnas de
        // la base de datos mapeada.
        System.out.println("Vamos a actualizar el nombre de la categoría 10, usando JPQL");

        // Obtenemos transacción
        EntityTransaction transaction = entityManager.getTransaction();

        // Hacemos la operación dentro de try/catch para poder hacer rollback si algo va mal
        try {
            // Iniciamos transacción
            transaction.begin();

            String newName = LocalTime.now().toString();

            // Para sintaxis de JPQL: https://en.wikibooks.org/wiki/Java_Persistence/JPQL
            // Las consultas JPQL se pueden probar en IntelliJ en la consola JPA (JPA console)
            Query updateQuery = entityManager.createQuery("update Category c set c.name = :newName where c.categoryId = :catId");
            // Los parámetros se ponen por nombre, no por índice como en Java.sql
            updateQuery
                    .setParameter("catId", TEST_CATEGORY_ID)
                    .setParameter("newName", newName);
            // Podemos obtener las filas afectadas con el valor de salida del método
            int rowCount = updateQuery.executeUpdate();
            System.out.printf("Se han actualizado %d filas.\n", rowCount);

            // Y completamos la transacción. Esto hace que se guarden los datos.
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
        System.out.println("Modificamos la categoría 12 con SQL nativo.");

        // Obtenemos transacción
        EntityTransaction transaction = entityManager.getTransaction();

        // Hacemos la operación dentro de try/catch para poder hacer rollback si algo va mal
        try {
            // Iniciamos transacción
            transaction.begin();

            // Creamos un objeto Query, con la sentencia update con parámetros
            Query updateQuery = entityManager.createNativeQuery("update category set name = :name where category_id = :catId");
            updateQuery.setParameter("name", LocalTime.now().toString()).setParameter("catId", TEST_CATEGORY_ID);
            int rowCount = updateQuery.executeUpdate();
            System.out.printf("Se han actualizado %d filas.\n", rowCount);

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
