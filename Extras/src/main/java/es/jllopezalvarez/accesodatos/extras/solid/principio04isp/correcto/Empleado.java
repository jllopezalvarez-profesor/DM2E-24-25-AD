package es.jllopezalvarez.accesodatos.extras.solid.principio04isp.correcto;

public class Empleado implements Trabajador {
    @Override
    public void trabajar() {
        System.out.println("El empleado está trabajando");
    }

    @Override
    public void descansar() {
        System.out.println("El empleado está descansando");
    }
}