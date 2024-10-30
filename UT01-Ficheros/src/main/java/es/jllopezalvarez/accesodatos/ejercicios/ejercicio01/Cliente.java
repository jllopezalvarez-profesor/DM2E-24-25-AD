package es.jllopezalvarez.accesodatos.ejercicios.ejercicio01;

import jakarta.xml.bind.annotation.XmlAttribute;

import java.util.List;

public class Cliente {
    private int id;

    private List<Alquiler> alquileres;

    @XmlAttribute
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Alquiler> getAlquileres() {
        return alquileres;
    }
}
