package es.jllopezalvarez.accesodatos.ut02.ejercicios.ejercicio03;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Programa {
    private static final String CONNECTION_STRING = "jdbc:mariadb://localhost/sakila";
    private static final String USERNAME = "sakilauser";
    private static final String PASSWORD = "pwdsakilauser";

    private static final int TAMANIO_LOTE = 3;
    private static final String SQL_INSERT = """
            insert into prueba_transaccion (contenido, fecha_creacion) 
            values (?, ?)""";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int contador = 0;

        try (Connection connection = DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(SQL_INSERT)) {
            connection.setAutoCommit(false);

            System.out.println("Introduce un valor para la columna contenido:");
            String contenido = scanner.nextLine();
            while (!contenido.equals("FIN")) {
                ps.setString(1, contenido);
                ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
                int numInsertados = ps.executeUpdate();
                System.out.printf("Se ha insertado %d registro\n", numInsertados);
                contador++;
                if (contador % TAMANIO_LOTE == 0) {
                    connection.commit();
                }
                System.out.println("Introduce un valor para la columna contenido:");
                contenido = scanner.nextLine();
            }
            // Si se comenta esta línea, los registros insertados y no confirmados
            // desde el último commit no se guardan (se hace rollback)
            // connection.commit();
            // Línea que provoca error--- -> Aunque hay error ya se ha confirmado
        } catch (SQLException e) {
            System.out.println("Error al insertar");
            e.printStackTrace();
        }
        System.out.printf("Gracias por usar este inútil programa");
    }
}
