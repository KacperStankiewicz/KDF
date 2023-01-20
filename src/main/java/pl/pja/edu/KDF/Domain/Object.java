package pl.pja.edu.KDF.Domain;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

import pl.pja.edu.KDF.Enumeration.ObjectCategory;
import lombok.*;


@Entity
@Table(name = "object")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Object {

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


    @NotNull
    @Column(name = "nip", nullable = false)
    private String nip;

    @Column(name = "name", nullable = false)
    @NotNull
    private String name;


    @ManyToOne
    private Owner owner;


}
