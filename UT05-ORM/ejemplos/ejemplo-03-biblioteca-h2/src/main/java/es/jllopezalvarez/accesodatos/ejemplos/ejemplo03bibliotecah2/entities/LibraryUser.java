package es.jllopezalvarez.accesodatos.ejemplos.ejemplo03bibliotecah2.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class LibraryUser {
    @Id
    private UUID id;
    @Column(nullable = false, unique = true)
    private String dni;
    @Column(nullable = false, length = 100)
    private String firstName;
    @Column(nullable = false, length = 100)
    private String lastName;
    @Column(nullable = false, length = 50)
    private String email;

    @OneToMany(mappedBy = "user")
    private List<Loan> loans;
}
