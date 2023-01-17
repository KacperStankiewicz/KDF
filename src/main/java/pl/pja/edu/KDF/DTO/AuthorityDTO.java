package pl.pja.edu.KDF.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.pja.edu.KDF.Domain.Authority;

import java.io.Serializable;

/**
 * A DTO for the {@link Authority} entity
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorityDTO implements Serializable {
    private String name;

}