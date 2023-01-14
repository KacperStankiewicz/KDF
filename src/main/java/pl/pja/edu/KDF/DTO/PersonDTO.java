package pl.pja.edu.KDF.DTO;


import pl.pja.edu.KDF.Domain.Person;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link Person} entity
 */

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<AuthorityDTO> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<AuthorityDTO> authorities) {
        this.authorities = authorities;
    }

    public ObjectDTO getObject() {
        return object;
    }

    public void setObject(ObjectDTO object) {
        this.object = object;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }
}