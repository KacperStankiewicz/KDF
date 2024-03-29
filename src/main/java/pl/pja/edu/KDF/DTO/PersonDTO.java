package pl.pja.edu.KDF.DTO;


import lombok.*;
import pl.pja.edu.KDF.Domain.Person;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link Person} entity
 */

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO implements Serializable {
    private Long id;
    @NotNull
    private String firstname;
    @NotNull
    private String lastname;
    @NotNull
    private String phone;
    private List<AuthorityDTO> authorities;
    private ObjectDTO object;
    @NotNull
    private AddressDTO address;

    @NotNull
    @Email
    private String email;
}