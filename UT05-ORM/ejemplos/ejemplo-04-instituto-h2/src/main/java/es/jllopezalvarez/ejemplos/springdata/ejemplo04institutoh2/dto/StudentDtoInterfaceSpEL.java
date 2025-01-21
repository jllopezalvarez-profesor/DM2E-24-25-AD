package es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.dto;

import org.springframework.beans.factory.annotation.Value;

public interface StudentDtoInterfaceSpEL {
    String getFirstName();
    String getLastName();
    @Value("#{target.firstName + ' ' + target.lastName}")
    String getFullName();
}
