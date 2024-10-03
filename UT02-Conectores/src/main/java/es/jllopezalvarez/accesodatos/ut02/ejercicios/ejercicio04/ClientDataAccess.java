package es.jllopezalvarez.accesodatos.ut02.ejercicios.ejercicio04;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ClientDataAccess {
    private static final String SQL_FIND_CLIENT_DETAILS = """
                    select *
                    from customer
                             join sakila.address on customer.address_id = address.address_id
                             join sakila.city on address.city_id = city.city_id
                             join sakila.country on city.country_id = country.country_id
                    where
                        first_name like ? or last_name like ?
            """;

    public static List<ClientDetails> findClientsDetails(String search) {
        /*
         * 0.- Crear una lista vacía
         * 1.- Abrir conexión - Pedirla al pool de conexiones
         * 2.- Crear una PreparedStatement con el SQL adecuado, y pasar parámetros
         * 3.- Ejecutar la PS y obtener un ResultSet
         * 4.- Recorrer Resultset y llenar la lista de ClientDetails
         * 5.- Cerrar la conexión
         * 6.- Devolver la lista
         * */
        // Paso 0
        List<ClientDetails> results = new ArrayList<>();

        // Paso 1 y paso 5 // El try-with-resources cierra la conexión
        try (Connection connection = SakilaConnectionPool.getInstance().getConnection();
             // Paso 2
             PreparedStatement ps = connection.prepareStatement(SQL_FIND_CLIENT_DETAILS)) {
            search = "%" + search + "%";
            ps.setString(1, search);
            ps.setString(2, search);

            // Paso 3
            try (ResultSet rs = ps.executeQuery()) {
                // Paso 4
                while (rs.next()) {
                    int clientId = rs.getInt("customer_id");
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    String email = rs.getString("email");
                    boolean active = rs.getBoolean("active");
                    LocalDateTime lastUpdate = rs.getTimestamp("last_update").toLocalDateTime();
                    String addressLine1 = rs.getString("address");
                    String addressLine2 = rs.getString("address2");
                    String district = rs.getString("district");
                    String city = rs.getString("city");
                    String country = rs.getString("country");

                    ClientDetails cd = new ClientDetails(clientId, firstName, lastName, email, active,
                            lastUpdate, addressLine1, addressLine2, district, city, country);

                    results.add(cd);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar clientes", e);
        }
        // Paso 6
        return results;
    }
}
