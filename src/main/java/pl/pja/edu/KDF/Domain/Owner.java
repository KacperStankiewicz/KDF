package pl.pja.edu.KDF.Domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "owner")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "firstname", nullable = false)
    private String firstname;


    @Column(name = "lastname", nullable = false)
    @NotNull
    private String lastname;

    @Column(name = "phone", nullable = false, length = 15)
    @NotNull
    private String phone;

    @ManyToMany
    private List<Authority> authorities;

    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false)
    @NotNull
    private Address address;

    @OneToMany
    private List<Object> objects;
}
