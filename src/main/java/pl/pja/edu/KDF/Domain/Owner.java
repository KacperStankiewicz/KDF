package pl.pja.edu.KDF.Domain;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import pl.pja.edu.KDF.Configuration.Constants;

import java.util.List;

@Entity
@Table(name = "owner")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Owner extends AbstractAuditingEntity<Long>{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 255)
    @Column(length = 255, unique = true, nullable = false)
    private String login;

    @JsonIgnore
    @NotNull
    @Size(min = 60, max = 60)
    @Column(name = "password_hash", length = 60, nullable = false)
    private String password;

    @NotNull
    @Column(name = "firstname", nullable = false)
    private String firstname;


    @Column(name = "lastname", nullable = false)
    @NotNull
    private String lastname;

    @Email
    @Size(min = 5, max = 255)
    @Column(length = 255, unique = true)
    @NotNull
    private String email;

    @NotNull
    @Column(nullable = false)
    private boolean activated = true;

    @Size(max = 15)
    @Column(name = "phone", nullable = false, length = 15)
    private String phone;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "owner_authority",
            joinColumns = { @JoinColumn(name = "owner_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "authority", referencedColumnName = "name") }
    )
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @BatchSize(size = 20)
    private List<Authority> authorities;

    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false)
    @NotNull
    private Address address;

    @OneToMany
    private List<Object> objects;
}
