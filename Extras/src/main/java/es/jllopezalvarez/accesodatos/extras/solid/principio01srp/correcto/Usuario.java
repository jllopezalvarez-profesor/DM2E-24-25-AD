package es.jllopezalvarez.accesodatos.extras.solid.principio01srp.correcto;

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
}
