package es.jllopezalvarez.accesodatos.extras.solid.principio02ocp.incorrecto;

public class Rectangulo {
    private final double ancho;
    private final double alto;

    public Rectangulo(double ancho, double alto) {
        this.ancho = ancho;
        this.alto = alto;
    }

    public double getAncho() {
        return ancho;
    }

    public double getAlto() {
        return alto;
    }
}