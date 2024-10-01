package es.jllopezalvarez.accesodatos.extras.singleton;


/**
 * Ejemplo de Singleton con inicialización temprana usando ENUM.
 * El atributo es por defecto estático y final, y se inicializa.
 * Como los enum no se pueden heredar, no tenemos que hacer constructor privado.
 * Es thread-safe porque en Java los bloques estáticos (y las declaraciones de
 * atributos estáticos) son thread-safe.
 * Es el enfoque más simple en código, pero más limitado en cuanto a extensibilidad.
 */

public enum EnumSingleton {
    INSTANCE; // Se puede inicializar aquí o en bloque static

    // Los métodos de instancia son los que se usan cuando ya se ha obtenido
    // una referencia a la instacia (al atributo estático)
    public void metodoDeInstancia(){
        // HAcer lo que sea que debe hacer.
        System.out.println("En método de instancia de EnumSingleton");
    }

}
