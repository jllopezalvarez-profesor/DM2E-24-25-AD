package es.jllopezalvarez.accesodatos.ut02.ejercicios.ejercicio01;

import java.sql.*;
import java.util.Scanner;

public class Programa {
    private static final String CONNECTION_STRING = "jdbc:mariadb://localhost/sakila";
    private static final String USERNAME = "sakilauser";
    private static final String PASSWORD = "pwdsakilauser";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce la sentencia que quieres ejecutar:");
        String query = sc.nextLine();
        while (!query.isBlank()) {
            try {
                ejecutarSentencia(query);
            } catch (Exception e) {
                System.out.println("Se ha producido un error al ejecutar la sentencia:");
                System.out.println(e.getMessage());
            }

            System.out.println("Introduce la sentencia que quieres ejecutar:");
            query = sc.nextLine();
        }
        System.out.println("Saliendo del programa");
    }

    private static void ejecutarSentencia(String query) throws SQLException {
        System.out.printf("Ejecutando sentencia: %s\n", query);
        try (Connection connection = DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(query)
        ) {
            boolean isResultset = ps.execute();

            if (isResultset) {
                System.out.println("Hay datos de la consulta");
                mostrarResultset(ps.getResultSet());
            } else {
                System.out.println("Es una actualización, inserción o borrado.");
                System.out.printf("Han sido afectados %d registros.\n", ps.getUpdateCount());
            }
        }
    }

    private static void mostrarResultset(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        mostrarCabeceraResultset(metaData);
        mostrarCuerpoResultset(resultSet, metaData);
    }

    private static void mostrarCabeceraResultset(ResultSetMetaData metaData) throws SQLException {
        int columnCount = metaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            System.out.printf("%s ", metaData.getColumnName(i));
        }
        System.out.println();

    }

    private static void mostrarCuerpoResultset(ResultSet resultSet, ResultSetMetaData metaData) throws SQLException {

        int columnCount = metaData.getColumnCount();
        while (resultSet.next()){
            for (int i = 1; i <= columnCount; i++) {
                System.out.printf("%s ", resultSet.getString(i));

            }
            System.out.println();

        }
        System.out.println();
    }
}
