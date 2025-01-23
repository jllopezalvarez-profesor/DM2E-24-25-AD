package es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.dto;

public interface StudentDtoInterfaceDefault {
    String getFirstName();
    String getLastName();
    default String getFullName(){
        return getFirstName() + " " + getLastName();
    };
}
