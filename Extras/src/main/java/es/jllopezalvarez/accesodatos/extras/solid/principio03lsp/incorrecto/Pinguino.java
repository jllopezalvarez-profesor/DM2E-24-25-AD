package es.jllopezalvarez.accesodatos.extras.solid.principio03lsp.incorrecto;

public class Pinguino extends Ave {
    @Override
    public void volar() {
        throw new UnsupportedOperationException("Los pingüinos no pueden volar");
    }
}