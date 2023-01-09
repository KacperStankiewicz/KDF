package pl.pja.edu.KDF.DTO;

import pl.pja.edu.KDF.Domain.Authority;
import pl.pja.edu.KDF.Enumeration.UserAuthorities;

import java.io.Serializable;

/**
 * A DTO for the {@link Authority} entity
 */

public class AuthorityDTO implements Serializable {
    private Long id;
    private UserAuthorities name;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserAuthorities getName() {
        return name;
    }

    public void setName(UserAuthorities name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}