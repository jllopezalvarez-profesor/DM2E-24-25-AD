package es.jllopezalvarez.accesodatos.extras.singleton;


/**
 * Ejemplo de Singleton con inicialización tardía.
 * El atributo estático se inicializa la primera vez que se necesita.
 * Es thread-safe porque dentro del método "getInstance()" se produce sincronización,
 * pero solo si no se ha inicializado antes.
 * Ventaja: No se crea el atributo hasta que se necesita por primera vez.
 * Menos problemático que LazySynchronizedSingleton, porque, aunque es sincronizado,
 * solo hay sincronización si no se ha inicializado el atributo estático.
 */

public class BetterLazySynchronizedSingleton {
    // No se inicializa en este punto porque no queremos crearlo de antemano.
    private static BetterLazySynchronizedSingleton instance;

    // Constructor privado para evitar que se puedan crear nuevas instancias
    private BetterLazySynchronizedSingleton() {
    }

    // Método estático y sincronizado para acceder a la instancia única.
    public static BetterLazySynchronizedSingleton getInstance() {
        if (instance == null) {
            synchronized (BetterLazySynchronizedSingleton.class) {
                // Hay que hacer doble comprobación, por si otro hilo consiguió inicializar el atributo antes
                if (instance == null) {
                    // Creamos la instancia solo la primera vez que se ejecuta el método,
                    // y siempre que no lo haya hecho otro hilo antes.
                    instance = new BetterLazySynchronizedSingleton();
                }
            }
        }
        return instance;
    }

    // Los métodos de instancia son los que se usan cuando ya se ha obtenido
    // una referencia a la instacia (al atributo estático)
    public void metodoDeInstancia(){
        // HAcer lo que sea que debe hacer.
        System.out.println("En método de instancia de BetterLazySynchronizedSingleton");
    }

}
