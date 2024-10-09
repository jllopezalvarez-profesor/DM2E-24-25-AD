package es.jllopezalvarez.accesodatos.extras.solid.principio05dip.correcto;

/**
 * Módulo de bajo nivel: clase que manda mensajes por SMS
 */
public class NotificadorSMS {
    public void enviar(String mensaje) {
        System.out.println("Enviando SMS: " + mensaje);
    }
}