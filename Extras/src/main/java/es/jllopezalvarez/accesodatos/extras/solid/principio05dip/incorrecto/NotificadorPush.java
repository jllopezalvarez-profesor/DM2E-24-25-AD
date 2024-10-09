package es.jllopezalvarez.accesodatos.extras.solid.principio05dip.incorrecto;

// Nueva clase de bajo nivel: Envío de notificaciones push
public class NotificadorPush implements Notificador {
    @Override
    public void enviar(String mensaje) {
        System.out.println("Enviando notificación push: " + mensaje);
    }
}
