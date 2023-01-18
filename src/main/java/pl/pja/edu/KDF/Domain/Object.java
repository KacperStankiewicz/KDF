package pl.pja.edu.KDF.Domain;

import lombok.*;
import pl.pja.edu.KDF.Enumeration.ObjectCategory;
import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "object")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Object  extends AbstractAuditingEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @OneToOne
    private Address address;

    @Column(name = "category", nullable = false)
    @Enumerated(EnumType.STRING)
    private ObjectCategory category;

    @Column(name = "name", nullable = false)
    @NotNull
    private String name;

    @Column(name = "nip", nullable = false)
    @NotNull
    private String nip;
}
