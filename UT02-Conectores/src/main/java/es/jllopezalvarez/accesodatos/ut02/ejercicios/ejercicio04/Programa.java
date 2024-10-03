package es.jllopezalvarez.accesodatos.ut02.ejercicios.ejercicio04;

import org.checkerframework.checker.regex.qual.Regex;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Programa {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String searchString = "";
        do {
            System.out.print("Introduce la cadena de búsqueda: ");
            searchString = scanner.nextLine();

            if (!searchString.isBlank()) {
                var results = ClientDataAccess.findClientsDetails(searchString);
                if (results.isEmpty()) {
                    System.out.printf("No se han encontrado clientes con '%s' en nombre o apellido.\n", searchString );
                } else {
                    showClientDetails(results);
                }
            }

        } while (!searchString.isBlank());


    }

    private static void showClientDetails(List<ClientDetails> results) {


        System.out.printf("Se han encontrado %s clientes:\n", results.size());
        results.forEach(System.out::println);

        // Ejemplo de como rfiltrar con streams usand expresiones regulares.
//        Regex regex = new Regex();
//        results.stream().filter(cd-> cd.getFirstName().matches(regex) || cd.getLastName().matches(regex))

    }
}
