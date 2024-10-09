package es.jllopezalvarez.accesodatos.extras.solid.principio05dip.incorrecto;

/**
 * Módulo de bajo nivel: clase que manda mensajes por SMS
 */
public class NotificadorSMS implements Notificador {
    @Override
    public void enviar(String mensaje) {
        System.out.println("Enviando SMS: " + mensaje);

    }
}