package es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class CreateStudentDto {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private List<Long> modules;

    public CreateStudentDto(String firstName, String lastName, LocalDate birthDate, List<Long> modules) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.modules = modules;
    }

    public CreateStudentDto() {
        this.modules = new ArrayList<>();
    }

    public List<Long> getModules() {
        return modules;
    }

    public void setModules(List<Long> modules) {
        this.modules = modules;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CreateStudentDto{");
        sb.append("firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", birthDate=").append(birthDate);
        sb.append('}');
        return sb.toString();
    }
}
