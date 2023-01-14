package pl.pja.edu.KDF.DTO;



import pl.pja.edu.KDF.Domain.Station;
import pl.pja.edu.KDF.Enumeration.StationStatus;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A DTO for the {@link Station} entity
 */

public class StationDTO implements Serializable {
    private Long id;
    @NotNull
    private String identifier;
    @NotNull
    private StationStatus status;
    @NotNull
    @Min(1)
    private Integer capacity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public StationStatus getStatus() {
        return status;
    }

    public void setStatus(StationStatus status) {
        this.status = status;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
}