package pl.pja.edu.KDF.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


@Getter
@Setter
public class PersonCreateDTO {

    @NotNull
    private String firstname;

    @NotNull
    private String lastname;

    @NotNull
    private String phone;

    @NotNull
    private Long objectId;

    @NotNull
    private AddressDTO address;

    @NotNull
    @Email
    private String email;
}
