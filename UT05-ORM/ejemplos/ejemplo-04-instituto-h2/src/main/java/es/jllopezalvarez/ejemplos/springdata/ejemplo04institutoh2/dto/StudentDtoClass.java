package es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.dto;

public class StudentDtoClass {
    private String firstName;
    private String lastName;

    public StudentDtoClass() {}

    public String getFullName(){
        return getFirstName() + " " + getLastName();
    };

    public StudentDtoClass(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
