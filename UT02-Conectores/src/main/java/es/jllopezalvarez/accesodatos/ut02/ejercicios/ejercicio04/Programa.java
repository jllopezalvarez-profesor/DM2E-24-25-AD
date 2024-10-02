package es.jllopezalvarez.accesodatos.ut02.ejercicios.ejercicio04;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Programa {
    public static void main(String[] args) {
        try (Connection conexion = SakilaConnectionPool.getInstance().getConnection();
             Statement s = conexion.createStatement()) {



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
