package es.jllopezalvarez.accesodatos.ut02.ejercicios.ejercicio04;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Pool de conexiones para la BD Sakila
 * Se conecta con una cadena de conexión, usuario y
 * contraseñas establecidos en constantes dentro de la clase
 */
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


    /**
     * Permite obtener una conexión a la BD
     * @return la conexión a la base de datos.
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
