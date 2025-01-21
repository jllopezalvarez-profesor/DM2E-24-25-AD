package es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.services;

import es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.dto.StudentDtoClass;
import es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.dto.StudentDtoInterfaceDefault;
import es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.dto.StudentDtoInterfaceSpEL;
import es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.dto.StudentDtoRecord;
import es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.entities.Student;
import es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.repositories.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    public StudentServiceImpl(StudentRepository studentRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
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

    @Override
    public List<StudentDtoInterfaceSpEL> getAllDtoInterfaceSpEL(){
        return studentRepository.findAllBy();
    }

    @Override
    public List<StudentDtoInterfaceDefault> getAllDtoInterfaceDefault(){
        return studentRepository.findAllByOrderByLastName();
    }

    @Override
    public List<StudentDtoClass> getAllDtoClass(){
        return studentRepository.findAllByOrderByFirstNameDesc();
    }

    @Override
    public List<StudentDtoRecord> getAllDtoRecord(){
        return studentRepository.findAllByOrderByLastNameAsc();
    }

    @Override
    public List<StudentDtoClass> getAllDtoClassMapped(){
        List<Student> students = studentRepository.findAll();
//        ModelMapper modelMapper = new ModelMapper();
        List<StudentDtoClass> mappedStudents = students.stream().map(student -> modelMapper.map(student, StudentDtoClass.class)).toList();
        return mappedStudents;
    }
}
