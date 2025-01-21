package es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.dto;

import org.springframework.beans.factory.annotation.Value;

public interface StudentDtoInterfaceDefault {
    String getFirstName();
    String getLastName();
    default String getFullName(){
        return getFirstName() + " " + getLastName();
    };
}
