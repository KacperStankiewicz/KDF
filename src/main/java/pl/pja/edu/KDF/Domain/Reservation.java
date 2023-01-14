package pl.pja.edu.KDF.Domain;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "start_date", nullable = false)
    @NotNull
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    @NotNull
    private LocalDateTime endDate;

    @Column(name = "email", nullable = false)
    @NotNull
    @Email
    private String email;

    @Column(name = "firstname", nullable = false)
    @NotNull
    private String firstname;

    @Column(name = "lastname", nullable = false)
    @NotNull
    private String lastname;

    @Column(name = "phone", nullable = false, length = 15)
    @NotNull
    private String phone;

    @Column(name = "num_of_people", nullable = false)
    @NotNull
    @Min(1)
    private Integer numberOfPeople;
}
