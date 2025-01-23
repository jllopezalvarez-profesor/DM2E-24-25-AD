package es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.dto;

public class CreateModuleDto {
    private String name;
    private Integer anualHours;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAnualHours() {
        return anualHours;
    }

    public void setAnualHours(Integer anualHours) {
        this.anualHours = anualHours;
    }
}
