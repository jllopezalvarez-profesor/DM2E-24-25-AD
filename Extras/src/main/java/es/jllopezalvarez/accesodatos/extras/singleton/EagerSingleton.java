package es.jllopezalvarez.accesodatos.extras.singleton;


/**
 * Ejemplo de Singleton con inicialización temprana.
 * El atributo estático se inicializa con un bloque static (o en su declaración)
 * Es thread-safe porque en Java los bloques estáticos (y las declaraciones de
 * atributos estáticos) son thread-safe.
 * Posible desventaja: se crea siempre el objeto aunque no se llegue a usar
 */

public class EagerSingleton {
    private static final EagerSingleton instance; // Se puede inicializar aquí o en bloque static

    // Inicialización en bloque static. Puede hacerse en la declaración del atributo.
    static {
        instance = new EagerSingleton();
    }

    // Constructor privado para evitar que se puedan crear nuevas instancias
    private EagerSingleton() {
    }

    // Método estático para acceder a la instancia única.
    public static EagerSingleton getInstance() {
        return instance;
    }

    // Los métodos de instancia son los que se usan cuando ya se ha obtenido
    // una referencia a la instacia (al atributo estático)
    public void metodoDeInstancia() {
        // HAcer lo que sea que debe hacer.
        System.out.println("En método de instancia de EagerSingleton");
    }

}
