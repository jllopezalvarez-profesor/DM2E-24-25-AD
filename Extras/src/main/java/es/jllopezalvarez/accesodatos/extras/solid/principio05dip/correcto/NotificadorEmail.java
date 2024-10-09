package es.jllopezalvarez.accesodatos.extras.solid.principio05dip.correcto;

/**
 * Módulo de bajo nivel: clase que manda mensajes por email
 */
public class NotificadorEmail {
    public void enviar(String mensaje) {
        System.out.println("Enviando email: " + mensaje);
    }
}