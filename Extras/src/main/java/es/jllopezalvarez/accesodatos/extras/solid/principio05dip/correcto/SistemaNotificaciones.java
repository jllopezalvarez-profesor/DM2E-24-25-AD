package es.jllopezalvarez.accesodatos.extras.solid.principio05dip.correcto;

// Módulo de alto nivel: Sistema de notificaciones que depende directamente de las implementaciones
public class SistemaNotificaciones {
    // Dependencia de una implementación, no de una abstracción
    private final NotificadorEmail notificadorEmail;
    // Dependencia de una implementación, no de una abstracción
    private final NotificadorSMS notificadorSMS;

    public SistemaNotificaciones(NotificadorEmail notificadorEmail, NotificadorSMS notificadorSMS   ) {
        this.notificadorEmail = notificadorEmail;
         this.notificadorSMS = notificadorSMS;
    }

    public SistemaNotificaciones() {
        this.notificadorEmail = new NotificadorEmail();  // Dependencia directa
        this.notificadorSMS = new NotificadorSMS();      // Dependencia directa
    }

    public void enviarNotificacion(String mensaje, String medio) {
        if (medio.equals("email")) {
            notificadorEmail.enviar(mensaje);
        } else if (medio.equals("sms")) {
            notificadorSMS.enviar(mensaje);
        }
        // Aquí se rompe SRP porque si queremos enviar otros tipos de notificaciones hay que cambiar este método.
    }
}
