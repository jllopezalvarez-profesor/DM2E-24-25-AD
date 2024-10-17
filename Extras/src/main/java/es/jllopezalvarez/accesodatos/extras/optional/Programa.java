package es.jllopezalvarez.accesodatos.extras.optional;

import java.sql.*;
import java.util.Optional;

public class Programa {
    private static final String CONNECTION_STRING = "jdbc:mariadb://localhost/sakila";
    private static final String USERNAME = "sakilauser";
    private static final String PASSWORD = "pwdsakilauser";
    private static final String SQL_FIND_FILM = "select film_id, title from film where film_id = ?";


    public static void main(String[] args) throws SQLException {
        Film f = findFilmByIdConNulos(5000);

        //f.getFilmId();

//        if (f != null) {
//            System.out.println(f.getFilmId());
//        }

        Optional<Film> f2 = findFilmByIdConOptional(5);

        if (f2.isPresent()) {
            int n = f2.orElseThrow().getFilmId();
        }
        if (f2.isPresent()){
            System.out.println(f2.get().getTitle());
        }


    }


    /**
     * Este método devuelve null,
     * @param filmId
     * @return
     * @throws SQLException
     */
    private static Film findFilmByIdConNulos(int filmId) throws SQLException {
        try (Connection connection = DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(SQL_FIND_FILM)) {
            ps.setInt(1, filmId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.isBeforeFirst()) {
                    rs.next();
                    Film f = new Film(rs.getInt("film_id"), rs.getString("title"));
                    ;
                    return f;
                }
            }
        }
        return null;
    }


    private static Optional<Film> findFilmByIdConOptional(int filmId) throws SQLException {
        try (Connection connection = DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(SQL_FIND_FILM)) {
            ps.setInt(1, filmId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.isBeforeFirst()) {
                    rs.next();
                    Film f = new Film(rs.getInt("film_id"), rs.getString("title"));
                    return Optional.of(f);
                }
            }
        }
        return Optional.empty();
    }

}
