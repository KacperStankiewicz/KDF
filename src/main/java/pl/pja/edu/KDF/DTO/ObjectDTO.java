package pl.pja.edu.KDF.DTO;


import pl.pja.edu.KDF.Enumeration.ObjectCategory;

import java.io.Serializable;

/**
 * A DTO for the {@link Object} entity
 */

public class ObjectDTO implements Serializable {
    private Long id;
    private AddressDTO address;
    private ObjectCategory category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public ObjectCategory getCategory() {
        return category;
    }

    public void setCategory(ObjectCategory category) {
        this.category = category;
    }
}