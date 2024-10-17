package es.jllopezalvarez.accesodatos.extras.optional;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Film {
    @EqualsAndHashCode.Include
    private final int filmId;
    private final String title;
}
