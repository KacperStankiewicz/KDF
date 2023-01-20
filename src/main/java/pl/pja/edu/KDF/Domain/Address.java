package pl.pja.edu.KDF.Domain;


import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "address")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address extends AbstractAuditingEntity<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "street", nullable = false, length = 255)
    @NotNull
    private String street;

    @Column(name = "number", nullable = false, length = 255)
    @NotNull
    private String number;

    @Column(name = "city", nullable = false, length = 255)
    @NotNull
    private String city;

    @Column(name = "country", nullable = false, length = 255)
    @NotNull
    private String country;

    @Column(name = "postal_code", nullable = false, length = 6)
    @NotNull
    private String postalCode;

}
