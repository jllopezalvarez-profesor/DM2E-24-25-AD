package es.jllopezalvarez.accesodatos.extras.singleton.ejemplo;

import java.util.List;

public class Programa {
    public static void main(String[] args) {


        ListaSingleton.getInstance().add(2);
        System.out.println(ListaSingleton.getInstance());

        ListaSingleton.getInstance().add(7);
        System.out.println(ListaSingleton.getInstance());

        metodo();
        System.out.println(ListaSingleton.getInstance());

    }

    private static void metodo(){
        ListaSingleton.getInstance().add(9);
    }

}
