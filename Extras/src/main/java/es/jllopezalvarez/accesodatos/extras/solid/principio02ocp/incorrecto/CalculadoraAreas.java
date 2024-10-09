package es.jllopezalvarez.accesodatos.extras.solid.principio02ocp.incorrecto;

/**
 * Implementación que no cumple con OCP.
 * El método "calcularAreas" tiene que ser modificado para poder calcular el área de un triángulo (o de otras figuras).
 */
public class CalculadoraAreas {
    public double calcularArea(Object figura) {
        if (figura instanceof Circulo circulo) {
            return Math.PI * Math.pow(circulo.getRadio(), 2);
        } else if (figura instanceof Rectangulo rectangulo) {
            return rectangulo.getAncho() * rectangulo.getAlto();
        }
        // Si quisieras agregar un triángulo, tendrías que modificar este método
        return 0;
    }
}
