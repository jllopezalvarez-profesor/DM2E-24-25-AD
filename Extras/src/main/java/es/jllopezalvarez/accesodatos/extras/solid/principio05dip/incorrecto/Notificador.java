package es.jllopezalvarez.accesodatos.extras.solid.principio05dip.incorrecto;

/**
 * Abstracción: cualquier notificador debe implementar este comportamiento
 */

public interface Notificador {
    void enviar(String mensaje);
}