package es.jllopezalvarez.ejemplos.springdata.ejemplo04institutoh2.dto;

public record StudentDtoRecord(String firstName, String lastName) {
    public String fullName() {
        return firstName + " " + lastName;
    }

}
