package pl.pja.edu.KDF.DTO;


import lombok.*;
import pl.pja.edu.KDF.Domain.Owner;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link Owner} entity
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OwnerDTO implements Serializable {
    private Long id;
    @NotNull
    private String firstname;
    @NotNull
    private String lastname;
    @NotNull
    private String phone;

    @NotNull
    private String email;
    private List<AuthorityDTO> authorities;
    @NotNull
    private AddressDTO address;

    private boolean activated;
}