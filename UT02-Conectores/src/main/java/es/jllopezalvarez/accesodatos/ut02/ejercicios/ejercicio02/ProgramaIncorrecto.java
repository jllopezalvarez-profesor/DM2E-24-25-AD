package es.jllopezalvarez.accesodatos.ut02.ejercicios.ejercicio02;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * Este programa no está bien porque ace rollback en el catch
 * cuando no es necesario,
 * al cerrarse la conexión se hace automáticamente rollback
 */
public class ProgramaIncorrecto {
    private static final String CONNECTION_STRING = "jdbc:mariadb://localhost/sakila";
    private static final String USERNAME = "sakilauser";
    private static final String PASSWORD = "pwdsakilauser";

    private static final String SQL_INSERT = """
            insert into prueba_transaccion (contenido, fecha_creacion) 
            values (?, ?)""";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        try {
            Connection connection = DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD);
            try (connection; PreparedStatement ps = connection.prepareStatement(SQL_INSERT)) {
                connection.setAutoCommit(false);

                System.out.println("Introduce un valor para la columna contenido:");
                String contenido = scanner.nextLine();
                while (!contenido.equals("FIN")) {
                    ps.setString(1, contenido);
                    ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
                    int numInsertados = ps.executeUpdate();
                    System.out.printf("Se ha insertado %d registro\n", numInsertados);
                    System.out.println("Introduce un valor para la columna contenido:");
                    contenido = scanner.nextLine();
                }
                connection.commit();
                // Línea que provoca error--- -> Aunque hay error ya se ha confirmado
            } catch (SQLException e) {
                System.out.println("Error al insertar");
                e.printStackTrace();
                connection.rollback();
            }
        } catch (SQLException e) {
            // Error al abrir la conexión
            System.out.println("Error al abrir la conexión ??");
            e.printStackTrace();
        }

        System.out.printf("Gracias por usar este inútil programa");

    }
}
