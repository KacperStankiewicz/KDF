package pl.pja.edu.KDF.Domain;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.*;
import pl.pja.edu.KDF.Enumeration.StationStatus;

@Entity
@Table(name = "station")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "identifier", nullable = false)
    @NotNull
    private String identifier;

    @Column(name = "status", nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    private StationStatus status;

    @Column(name = "capacity", nullable = false)
    @NotNull
    @Min(1)
    private Integer capacity;
}
