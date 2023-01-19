package pl.pja.edu.KDF.DTO;


import pl.pja.edu.KDF.Domain.Reservation;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * A DTO for the {@link Reservation} entity
 */

public class ReservationDTO implements Serializable {
    private Long id;
    @NotNull
    private LocalDateTime startDate;
    @NotNull
    private LocalDateTime endDate;
    @NotNull
    @Email
    private String email;
    @NotNull
    private String firstname;
    @NotNull
    private String lastname;
    @NotNull
    private String phone;
    @NotNull
    @Min(1)
    private Integer numberOfPeople;

    private StationDTO stationDTO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(Integer numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }
}