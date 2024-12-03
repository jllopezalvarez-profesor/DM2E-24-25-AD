package es.jllopezalvarez.ejemplos.orm.hibernate.programas;

import es.jllopezalvarez.ejemplos.orm.hibernate.entities.Category;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.List;


public class Ejemplo01Leer {

    private static final String SAKILA_PERSISTENCE_UNIT = "sakila";

    private static final int TEST_CATEGORY_ID = 10;

    public static void main(String[] args) {
        // Obtenemos una factoría de entityManagers para la unidad de persistencia de la BD Sakila
        // Con la factoría, obtenemos el EntityManager.
        // Usamos try-with-resources para que se cierren automáticamente.
        try (EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(SAKILA_PERSISTENCE_UNIT); EntityManager entityManager = entityManagerFactory.createEntityManager()) {

            // Opción 1: buscar un elemento por su ID
            usandoFind(entityManager);

            // Opción 2: buscar con getReference.
            usandoGetReference(entityManager);

            // Opción 3: buscar con un criterio usando objetos de hibernate
            usandoCriteriaBuilder(entityManager);

            // Opción 4: usar JPQL
            usandoJpql(entityManager);

            // Opción 5: usar SQL nativo
            usandoSqlNativo(entityManager);
        }

    }


    private static void usandoFind(EntityManager entityManager) {
        // El método devuelve null si no encuentra el objeto.
        // Lanza excepción is la clase no es una entidad (no está en la Persistence Unit)
        // o si el tipo de la clave no es compatible o no se puede convertir al tipo adecuado.
        System.out.printf("Buscamos categoría con ID %s, usando find.\n", TEST_CATEGORY_ID);
        Category categoryFind = entityManager.find(Category.class, TEST_CATEGORY_ID);
        System.out.println("Resultado de la búsqueda:");
        System.out.println(categoryFind);
        System.out.println();
    }

    private static void usandoGetReference(EntityManager entityManager) {
        System.out.printf("Buscamos categoría con ID %s, usando getReference.\n", TEST_CATEGORY_ID);
        // Este método no devuelve una entidad completa. Sólo devuelve un "proxy" "lazy" que contiene sólo la
        // clave primaria de la entidad. El resto de atributos se cargan al acceder por primera ve a alguno de ellos.
        // Observese que podemos buscar con valor de clave String, porque se puede convertir a int.
        Category categoryGetRef = entityManager.getReference(Category.class, String.valueOf(TEST_CATEGORY_ID));
        System.out.println("Resultado de la búsqueda (sólo ID):");
        System.out.println(categoryGetRef.getCategoryId()); // Esto en realidad no lanza consulta
        System.out.println("Resultado de la búsqueda (completo):");
        System.out.println(categoryGetRef);
        // La principal utilidad de este método es crear entidades para relacionarlas con otras, para establecer
        // relaciones en la BBDD. Se puede usar con ID que no existen en la BD, y no falla, pero cuando intentamos
        // acceder a los datos del proxy, fallará.
        System.out.println();
    }

    private static void usandoCriteriaBuilder(EntityManager entityManager) {
        System.out.println("Buscamos categorías con ID > 5, y que contengan 'me', usando CriteriaBuilder, CriteriaQuery y Predicate.");
        // Obtener un objeto para construir criterios:
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        // Creamos un criterio para entidades de tipo "Category"
        CriteriaQuery<Category> categoryCriteria = criteriaBuilder.createQuery(Category.class);
        // Creamos un objeto "Root" que representa un objeto del modelo, de la unidad de persistencia
        // Lo usaremos para expresiones lambda, que generarán los criterios.
        Root<Category> rootCategory = categoryCriteria.from(Category.class);
        // Creamos predicados para identificar los elementos que queremos recuperar.
        // Este predicado lo creamos para obtener las categorías con ID mayor que cinco
        Predicate idGtCinco = criteriaBuilder.gt(rootCategory.get("categoryId"), 5);
        // Este otro para las que tienen "me" en su nombre (like '%me%')
        Predicate nameLikeMe = criteriaBuilder.like(rootCategory.get("name"), "%me%");
        // Añadimos los predicados al criterio. Si lo hacemos con la sobrecarga que recibe varios predicados,
        // es como hacer un "and". Para hacer un or, hay que combinar los predicados en un nuevo predicado.
        // Versión "and"
        categoryCriteria.where(idGtCinco, nameLikeMe);
        // Versión "or" (comentada)
        // Predicate or = criteriaBuilder.or(idGtCinco, nameLikeMe);
        // categoryCriteria.where(or);
        // Creamos una query con tipo, para devolver listas de elementos
        TypedQuery<Category> categoryQuery = entityManager.createQuery(categoryCriteria);
        // Obtenemos la lista. Mostramos solo los ID para que ocupe menos la salida.
        List<Category> resultados = categoryQuery.getResultList();
        System.out.println(resultados.stream().map(Category::getCategoryId).toList());
    }


    private static void usandoJpql(EntityManager entityManager) {
        // El lenguaje JPQL es similar al lenguaje SQL, pero permite hacer consultas usando los
        // nombres de clases y nombres de atributos en lugar de los nombres de columnas de
        // la base de datos mapeada.
        System.out.printf("Buscamos la categoría con ID = %s, usando JPQL.\n", TEST_CATEGORY_ID);
        // Para sintaxis de JPQL: https://en.wikibooks.org/wiki/Java_Persistence/JPQL
        // Las consultas JPQL se pueden probar en IntelliJ en la consola JPA (JPA console)
        TypedQuery<Category> singleItemQuery = entityManager.createQuery("select c from Category c where c.categoryId = :catId", Category.class);
        // Los parámetros se ponen por nombre, no por índice como en Java.sql
        singleItemQuery.setParameter("catId", TEST_CATEGORY_ID);
        // Si estamos buscando solo un elemento, usaremos getSingleResult,
        Category category = singleItemQuery.getSingleResult();
        System.out.println(category);


        // También podemos hacer consultas que devuelven varios elementos
        TypedQuery<Category> multipleItemsQuery = entityManager.createQuery("select c from Category c where c.name like :search", Category.class);
        multipleItemsQuery.setParameter("search", "%ma%");
        List<Category> categories = multipleItemsQuery.getResultList();
        // Mostramos los id de los elementos recuperados
        System.out.println(categories.stream().map(c -> String.format("%s - %s", c.getCategoryId(), c.getName())).toList());
    }

    private static void usandoSqlNativo(EntityManager entityManager) {
        System.out.printf("Buscamos la categoría con ID = %s, usando SQL Nativo.\n", TEST_CATEGORY_ID);
        Query singleItemQuery = entityManager.createNativeQuery("select category_id, name, last_update from category where category_id = :catId", Category.class);
        // Los parámetros se ponen por nombre, no por índice como en Java.sql
        singleItemQuery.setParameter("catId", TEST_CATEGORY_ID);
        // Si estamos buscando solo un elemento, usaremos getSingleResult.
        // En este caso hay que hacer cast, porque la query usada no es TypedQuery
        Category category = (Category) singleItemQuery.getSingleResult();
        System.out.println(category);


        // También podemos hacer consultas que devuelven varios elementos
        Query multipleItemsQuery = entityManager.createNativeQuery("select category_id, name, last_update from category where name like :search", Category.class);
        multipleItemsQuery.setParameter("search", "%ma%");
        List<Category> categories = (List<Category>) multipleItemsQuery.getResultList();
        // Mostramos los id de los elementos recuperados
        System.out.println(categories.stream().map(c -> String.format("%s - %s", c.getCategoryId(), c.getName())).toList());
    }
}
