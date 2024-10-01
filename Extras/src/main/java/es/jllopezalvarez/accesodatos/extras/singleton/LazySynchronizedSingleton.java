package es.jllopezalvarez.accesodatos.extras.singleton;


/**
 * Ejemplo de Singleton con inicialización tardía.
 * El atributo estático se inicializa la primera vez que se necesita.
 * Es thread-safe porque el método "getInstance()" es sincronizado.
 * Ventaja: No se crea el atributo hasta que se necesita por primera vez.
 * Inconveniente: La sincronización del método getInstance hace que sea más lento.
 */

public class LazySynchronizedSingleton {
    // No se inicializa en este punto porque no queremos crearlo de antemano.
    private static LazySynchronizedSingleton instance;

    // Constructor privado para evitar que se puedan crear nuevas instancias
    private LazySynchronizedSingleton() {
    }

    // Método estático y sincronizado para acceder a la instancia única.
    public static synchronized LazySynchronizedSingleton getInstance() {
        if (instance == null) {
            // Creamos la instancia sólo la primera vez que se ejecuta el método
            instance = new LazySynchronizedSingleton();
        }
        return instance;
    }

    // Los métodos de instancia son los que se usan cuando ya se ha obtenido
    // una referencia a la instacia (al atributo estático)
    public void metodoDeInstancia() {
        // HAcer lo que sea que debe hacer.
        System.out.println("En método de instancia de LazySynchronizedSingleton");
    }

}
