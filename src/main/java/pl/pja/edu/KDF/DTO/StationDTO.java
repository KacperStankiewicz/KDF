package pl.pja.edu.KDF.DTO;


import lombok.Getter;
import lombok.Setter;
import pl.pja.edu.KDF.Domain.Station;
import pl.pja.edu.KDF.Enumeration.StationStatus;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * A DTO for the {@link Station} entity
 */

@Getter
@Setter
public class StationDTO {
    private Long id;
    @NotNull
    private String identifier;
    @NotNull
    private StationStatus status;
    @NotNull
    @Min(1)
    private Integer capacity;

    @NotNull
    @Min(1)
    private Integer StationNumber;

    @NotNull
    private ObjectDTO object;
}