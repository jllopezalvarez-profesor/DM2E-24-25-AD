package es.jllopezalvarez.accesodatos.ejemplos.ejemplo03bibliotecah2.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "book_id", "loan_date"})

} )
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDate loanDate;
    private LocalDate returnDate;

    @ManyToOne
    private Book book;

    @ManyToOne
    private LibraryUser user;
}
