package es.jllopezalvarez.accesodatos.extras.solid.principio03lsp.correcto;

public class Pinguino extends Ave {
    public void nadar() {
        System.out.println("Este pingüino está nadando");
    }

    @Override
    void mover() {
        nadar();
    }
}