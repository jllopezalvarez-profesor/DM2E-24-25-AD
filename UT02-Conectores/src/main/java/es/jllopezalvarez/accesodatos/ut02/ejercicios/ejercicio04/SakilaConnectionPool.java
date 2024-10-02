package es.jllopezalvarez.accesodatos.ut02.ejercicios.ejercicio04;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;

import java.sql.Connection;
import java.sql.SQLException;

public class SakilaConnectionPool {

    @Getter
    private static SakilaConnectionPool instance = new SakilaConnectionPool();

    private static final String CONNECTION_STRING = "jdbc:mariadb://localhost/sakila";
    private static final String USERNAME = "sakilauser";
    private static final String PASSWORD = "pwdsakilauser";

    private HikariDataSource dataSource;

    private SakilaConnectionPool() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(CONNECTION_STRING);
        config.setUsername(USERNAME);
        config.setPassword(PASSWORD);

        dataSource = new HikariDataSource(config);

    }


    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
