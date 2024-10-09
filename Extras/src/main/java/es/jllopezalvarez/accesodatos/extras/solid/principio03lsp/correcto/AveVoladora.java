package es.jllopezalvarez.accesodatos.extras.solid.principio03lsp.correcto;

public class AveVoladora extends Ave {
    public void volar() {
        System.out.println("Esta ave está volando");
    }

    @Override
    void mover() {
        volar();
    }
}