package es.jllopezalvarez.ejemplos.orm.hibernate.programas;

import es.jllopezalvarez.ejemplos.orm.hibernate.entities.Category;
import jakarta.persistence.*;

public class Ejemplo04Borrar {

    private static final String SAKILA_PERSISTENCE_UNIT = "sakila";

    private static final int TEST_CATEGORY_ID = 81;

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
//            usandoRemoveYEntidad(entityManager);

            // Opción 2: Usando una referencia (getReference)
            usandoRemoveYReferencia(entityManager);

            // Opción 3: usando JPQL
//            usandoJpql(entityManager);

            // Opción 4: Usando SQL nativo
//            usandoSqlNativo(entityManager);
        }

    }


    private static void usandoRemoveYEntidad(EntityManager entityManager) {
        System.out.println("Borramos una entidad usando el método remove del entity manager.");

        // El entity manager mantiene un control sobre todas las entidades que recupera de la base de datos,
        // identificando los cambios que se van produciendo en ellas. Si llamamos al método remove con una entidad,
        // la eliminará de la base de datos.

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

            // Ahora simplemente llamamos al método remove
            entityManager.remove(category);

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

    private static void usandoRemoveYReferencia(EntityManager entityManager) {
        System.out.println("Borramos una entidad usando el método remove del entity manager, pero con referencia.");

        // Es idéntico al borrado con entidad, pero se usa una referencia obtenida con geReference,
        // y no una entidad "completa"

        // Obtenemos transacción
        EntityTransaction transaction = entityManager.getTransaction();

        // Hacemos la operación dentro de try/catch para poder hacer rollback si algo va mal
        try {
            // Iniciamos transacción
            transaction.begin();

            // Primero recuperamos una categoría con el método find
            Category category = entityManager.getReference(Category.class, TEST_CATEGORY_ID);

            // En este caso si la categoría no existe en la BD no se produce ningún error en este punto.
            // Se producirá al hacer el commit de la transacción.

            // Ahora simplemente llamamos al método remove
            entityManager.remove(category);

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
        System.out.printf("Vamos a eliminar la categoría %s, usando JPQL\n", TEST_CATEGORY_ID);

        // Obtenemos transacción
        EntityTransaction transaction = entityManager.getTransaction();

        // Hacemos la operación dentro de try/catch para poder hacer rollback si algo va mal
        try {
            // Iniciamos transacción
            transaction.begin();

            // Para sintaxis de JPQL: https://en.wikibooks.org/wiki/Java_Persistence/JPQL
            // Las consultas JPQL se pueden probar en IntelliJ en la consola JPA (JPA console)
            Query updateQuery = entityManager.createQuery("delete Category c where c.categoryId = :catId");
            // Los parámetros se ponen por nombre, no por índice como en Java.sql
            updateQuery.setParameter("catId", TEST_CATEGORY_ID);
            // Podemos obtener las filas afectadas con el valor de salida del método
            int rowCount = updateQuery.executeUpdate();
            System.out.printf("Se han eliminado %d filas.\n", rowCount);

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
        System.out.printf("Eliminamos la categoría %s con SQL nativo.\n", TEST_CATEGORY_ID);

        // Obtenemos transacción
        EntityTransaction transaction = entityManager.getTransaction();

        // Hacemos la operación dentro de try/catch para poder hacer rollback si algo va mal
        try {
            // Iniciamos transacción
            transaction.begin();

            // Creamos un objeto Query, con la sentencia delete con parámetros
            Query updateQuery = entityManager.createNativeQuery("delete from category where category_id = :catId");
            updateQuery.setParameter("catId", TEST_CATEGORY_ID);
            int rowCount = updateQuery.executeUpdate();
            System.out.printf("Se han eliminado %d filas.\n", rowCount);

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
