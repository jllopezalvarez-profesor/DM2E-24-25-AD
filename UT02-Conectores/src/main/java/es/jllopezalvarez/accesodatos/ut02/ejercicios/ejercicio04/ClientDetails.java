package es.jllopezalvarez.accesodatos.ut02.ejercicios.ejercicio04;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class ClientDetails {
    @EqualsAndHashCode.Include
    @ToString.Include
    private final int customerId;
    @ToString.Include
    private final String firstName;
    @ToString.Include
    private final String lastName;
    private final String email;
    private final boolean active;
    private final LocalDateTime lastUpdate;
    @Getter(AccessLevel.NONE)
    private final String addressLine1;
    @Getter(AccessLevel.NONE)
    private final String addressLine2;
    private final String district;
    private final String city;
    private final String country;

    public String getFullName() {
        return String.format("%s %s", firstName, lastName);
    }

    public String getAddress() {
        return String.format("%s\n%s", addressLine1, addressLine2);
    }





}
