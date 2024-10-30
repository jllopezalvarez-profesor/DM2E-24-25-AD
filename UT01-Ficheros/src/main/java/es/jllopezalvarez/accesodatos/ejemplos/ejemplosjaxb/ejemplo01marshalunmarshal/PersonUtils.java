package es.jllopezalvarez.accesodatos.ejemplos.ejemplosjaxb.ejemplo01marshalunmarshal;

import net.datafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class PersonUtils {
    /**
     * Constructor para que la clase de utilidad no se pueda instanciar
     */
    private PersonUtils() {
    }

    private static Faker faker = new Faker();

    public static List<Person> createRandomPeople(int count) {
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            people.add(createRandomPerson());
        }
        return people;
    }

    public static Person createRandomPerson() {
        return new Person(faker.idNumber().valid(),
                faker.name().firstName(),
                faker.name().lastName());
    }
}
