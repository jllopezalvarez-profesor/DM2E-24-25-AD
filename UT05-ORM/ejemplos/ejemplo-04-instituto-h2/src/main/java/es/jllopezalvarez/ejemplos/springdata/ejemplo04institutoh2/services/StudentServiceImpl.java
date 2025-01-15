package es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.services;

import es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.entities.Student;
import es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.repositories.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> findById(Integer id) {
        return studentRepository.findById(id);
    }

    // Obtener paginado, y ordenado por nombre y apellidos
    @Override
    public List<Student> findAllPaged(int page, int pageSize) {
        PageRequest pageRequest = PageRequest .of(page, pageSize, Sort.by("firstName", "lastName"));
        Page<Student> studentsPage = studentRepository.findAll(pageRequest);
        return studentsPage.toList();
    }

    @Override
    public List<Student> findByName(String name) {
        return studentRepository.findStudentsByFirstNameContainingIgnoreCase(name);
    }

    @Override
    public List<Student> findByNameAndLastName(String firstName, String lastName) {
        return studentRepository.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseOrderByStudentIdAsc(firstName, lastName);
    }

    @Override
    public Long countUsingSql() {
        return studentRepository.countStudentsWithSQL();
    }

    @Override
    public List<Student> findContaningName(String name) {
        return studentRepository.findContainingInName(name);
    }
}
