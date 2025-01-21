package es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.services;

import es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.dto.StudentDtoClass;
import es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.dto.StudentDtoInterfaceDefault;
import es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.dto.StudentDtoInterfaceSpEL;
import es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.dto.StudentDtoRecord;
import es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.entities.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> findAll();

    Optional<Student> findById(Integer id);

    List<Student> findAllPaged(int page, int pageSize);

    List<Student> findByName(String name);

    List<Student> findByNameAndLastName(String firstName, String lastName);

    Long countUsingSql();

    List<Student> findContaningName(String name);

    List<StudentDtoInterfaceSpEL> getAllDtoInterfaceSpEL();

    List<StudentDtoInterfaceDefault> getAllDtoInterfaceDefault();


    List<StudentDtoClass> getAllDtoClass();

    List<StudentDtoRecord> getAllDtoRecord();

    List<StudentDtoClass> getAllDtoClassMapped();
}
