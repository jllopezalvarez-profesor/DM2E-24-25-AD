package es.jllopezalvarez.accesodatos.extras.singleton.ejemplo;

import lombok.Getter;

import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Clase que debe existir una sola vez (una sola instancia) y que sirve
 * para compartir la instancia entre todas las clases del programa
 */
public class ListaSingleton {

    private final List<Integer> lista;



    @Getter
    private static final ListaSingleton instance = new ListaSingleton();

    // Para no crear más instancias, constructor privado
    private ListaSingleton() {
        int tamanioInicial = new Random().nextInt(10,20);
        this.lista = new ArrayList<>(tamanioInicial);
    }


    public void add(Integer elemento) {
        lista.add(elemento);
    }

    public String toString() {
        return lista.toString();
    }

}
