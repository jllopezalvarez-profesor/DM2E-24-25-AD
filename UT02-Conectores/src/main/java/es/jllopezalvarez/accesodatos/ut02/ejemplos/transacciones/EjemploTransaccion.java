package es.jllopezalvarez.accesodatos.ut02.ejemplos.transacciones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EjemploTransaccion {
    private static final String CONNECTION_STRING = "jdbc:mariadb://localhost/sakila";
    private static final String USERNAME = "sakilauser";
    private static final String PASSWORD = "pwdsakilauser";
    private static final String SQL_INSERT_PAIS = "insert into country (country) values (?)";

    public static void main(String[] args) throws SQLException {
        try (Connection connection = DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD)) {
            // Ponemos transacciones explícitas
            connection.setAutoCommit(false);
            // Inserto un valor en la BD
            try {
                InsertarCountry("Fraciaaaaa", connection);
                InsertarCountry("Italiaaaaa", connection);
                InsertarCountry("Españaaaaa", connection);
                InsertarCountry("Irlandaaaa", connection);
                InsertarCountry("Fraciaaaaa pero no creo que esto quepa en la columna porque es muy ancho", connection);
                connection.commit();
            } catch (SQLException e) {
                System.out.println("Error:" + e.getMessage());
                System.out.println("Deshago la transacción");
                connection.rollback();
            }
        }
    }

    private static void InsertarCountry(String nombrePais, Connection connection) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(SQL_INSERT_PAIS)) {
            ps.setString(1, nombrePais);
            int numInsertados = ps.executeUpdate();
            System.out.printf("Se han insertado %d registros\n", numInsertados);
        }
    }
}
