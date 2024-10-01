package es.jllopezalvarez.accesodatos.ut02.ejemplos.poolconexiones.ejemplo01;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Programa {

    private static final String CONNECTION_STRING = "jdbc:mariadb://localhost/sakila";
    private static final String USERNAME = "sakilauser";
    private static final String PASSWORD = "pwdsakilauser";

    private static  final String UPDATE_INUTIL = "update customer set first_name = ? where customer_id = 20";

    public static void main(String[] args) throws SQLException {
        HikariConfig connectionPoolConfig = new HikariConfig();
        connectionPoolConfig.setJdbcUrl(CONNECTION_STRING);
        connectionPoolConfig.setUsername(USERNAME);
        connectionPoolConfig.setPassword(PASSWORD);

        connectionPoolConfig.setMaximumPoolSize(1);
        connectionPoolConfig.setConnectionTimeout(5000);

        try (HikariDataSource dataSource = new HikariDataSource(connectionPoolConfig)) {

            try (Connection connection1 = dataSource.getConnection();
                 PreparedStatement ps = connection1.prepareStatement(UPDATE_INUTIL)) {
                ps.setString(1, " Nuevo nombre");
                int numRegistros = ps.executeUpdate();
                System.out.printf("Se han actualizado %d registros (ps1)\n", numRegistros );

                System.out.println("El tipo de la conexión obtenida con el pool es:");
                System.out.println(connection1.getClass().getSimpleName());

                try (Connection connection2 = dataSource.getConnection();
                     PreparedStatement ps2 = connection2.prepareStatement(UPDATE_INUTIL)) {
                    ps2.setString(1, " Nuevo nombre");
                    int numRegistros2 = ps2.executeUpdate();
                    System.out.printf("Se han actualizado %d registros (ps2)\n", numRegistros2 );


                }

            }


        }




    }



}
