package es.jllopezalvarez.accesodatos.extras.solid.principio01srp.incorrecto;

/**
 * Implementación que no cumple con SRP. La autenticación no debería estar en la clase Usuario.
 */
public class Usuario {
    private final String nombre;
    private final String email;

    public Usuario(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public boolean autenticar(String password) {
        // Lógica de autenticación.
        // Lanzamos excepción para que compile.
        throw new UnsupportedOperationException("Funcionalidad pendiente de implementar");
    }
}
