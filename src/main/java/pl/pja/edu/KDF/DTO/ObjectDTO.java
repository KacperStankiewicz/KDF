package pl.pja.edu.KDF.DTO;


import lombok.*;
import pl.pja.edu.KDF.Enumeration.ObjectCategory;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A DTO for the {@link Object} entity
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ObjectDTO implements Serializable {

    private Long id;

    @NotNull
    private AddressDTO address;

    @NotNull
    private ObjectCategory category;

    @NotNull
    private String nip;

    @NotNull
    private String name;

    @NotNull
    private OwnerDTO owner;
}