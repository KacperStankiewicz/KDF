package pl.pja.edu.KDF.Domain;

import javax.persistence.*;
import pl.pja.edu.KDF.Enumeration.ObjectCategory;
import lombok.*;


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

}
