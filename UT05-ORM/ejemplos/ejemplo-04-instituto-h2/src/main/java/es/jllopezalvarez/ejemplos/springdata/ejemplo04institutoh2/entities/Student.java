package es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentId;
    @Column(length = 100, nullable = false)
    private String firstName;
    @Column(length = 100, nullable = false)
    private String lastName;
    @Column(nullable = false)
    private LocalDate dateOfBirth;

    public Student() {
    }

    public Student(Integer studentId, String firstName, String lastName, LocalDate dateOfBirth) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    @ManyToMany
    @JoinTable(name = "modules_students",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "module_id")},
            uniqueConstraints = {
                    @UniqueConstraint(columnNames = {"student_id", "module_id"})
            }

    )
    @JsonManagedReference
    private List<Module> modules;

    public List<Module> getModules() {
        return modules;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
}
