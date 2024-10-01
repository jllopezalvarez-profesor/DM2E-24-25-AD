package es.jllopezalvarez.accesodatos.extras.singleton;

public class Programa {
    public static void main(String[] args) {
        System.out.println("Prueba de distintos tipos de singleton");

        // Uso de los distintos singleton.
        // Todos son básicamente iguales en su uso
        EagerSingleton eagerSingleton = EagerSingleton.getInstance();
        eagerSingleton.metodoDeInstancia();

        // No se puede porque el constructor es privado.
        // EagerSingleton otro = new EagerSingleton();


        LazySynchronizedSingleton lazySynchronizedSingleton = LazySynchronizedSingleton.getInstance();
        lazySynchronizedSingleton.metodoDeInstancia();

        // Podemos ahorrarnos la variable:
        BetterLazySynchronizedSingleton.getInstance().metodoDeInstancia();

        // En los enum el atributo, por convención, es una constante, y se escribe en mayúsculas
        EnumSingleton.INSTANCE.metodoDeInstancia();
    }
}
