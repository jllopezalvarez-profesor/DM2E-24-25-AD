package es.jllopezalvarez.accesodatos.extras.solid.principio02ocp.incorrecto;

public class Triangulo {
    private final double base;
    private final double altura;

    public Triangulo(double base, double altura) {
        this.base = base;
        this.altura = altura;
    }

    public double getBase() {
        return base;
    }

    public double getAltura() {
        return altura;
    }
}