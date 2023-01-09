package pl.pja.edu.KDF.Domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "person")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "firstname", nullable = false)
    @NotNull
    private String firstname;

    @Column(name = "lastname", nullable = false)
    @NotNull
    private String lastname;

    @Column(name = "phone", nullable = false, length = 15)
    @NotNull
    private String phone;

    @ManyToMany
    private List<Authority> authorities;

    @ManyToOne
    private Object object;

    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    @NotNull
    private Address address;
}
