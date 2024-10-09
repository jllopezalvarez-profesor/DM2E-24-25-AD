package es.jllopezalvarez.accesodatos.extras.solid.principio05dip.incorrecto;

public class Programa {
    public static void main(String[] args) {
        // Creamos notificadores específicos (email, SMS, push)
        Notificador notificadorEmail = new NotificadorEmail();
        Notificador notificadorSMS = new NotificadorSMS();
        Notificador notificadorPush = new NotificadorPush();

        // Inyectamos un notificador específico al crear el sistema de notificaciones
        // Esta inyección se denomina inyección por constructor.
        SistemaNotificaciones sistemaEmail = new SistemaNotificaciones(notificadorEmail);
        SistemaNotificaciones sistemaSMS = new SistemaNotificaciones(notificadorSMS);
        SistemaNotificaciones sistemaPush = new SistemaNotificaciones(notificadorPush);

        sistemaEmail.enviarNotificacion("Hola por email");
        sistemaSMS.enviarNotificacion("Hola por SMS");
        sistemaPush.enviarNotificacion("Hola por notificación push");
    }
}
