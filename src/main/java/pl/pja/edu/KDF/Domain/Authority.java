package pl.pja.edu.KDF.Domain;


import javax.persistence.*;
import lombok.*;
import pl.pja.edu.KDF.Enumeration.UserAuthorities;

@Entity
@Table(name = "authority")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserAuthorities name;

    @Column(name = "description")
    private String description;

}
