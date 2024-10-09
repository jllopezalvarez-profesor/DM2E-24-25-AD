package es.jllopezalvarez.accesodatos.extras.solid.principio05dip.incorrecto;

// Módulo de alto nivel: Sistema de notificaciones que depende directamente de las abstracciones, no de implementaciones concretas

public class SistemaNotificaciones {
    private final Notificador notificador;

    // Inyectamos la dependencia a través del constructor
    public SistemaNotificaciones(Notificador notificador) {
        this.notificador = notificador;
    }

    public void enviarNotificacion(String mensaje) {
        notificador.enviar(mensaje);
    }
}