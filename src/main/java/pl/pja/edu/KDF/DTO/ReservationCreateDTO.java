package pl.pja.edu.KDF.DTO;

public class ReservationCreateDTO {
    private ReservationDTO reservationDTO;

    private String reCaptchaToken;

    public ReservationDTO getReservationDTO() {
        return reservationDTO;
    }

    public void setReservationDTO(ReservationDTO reservationDTO) {
        this.reservationDTO = reservationDTO;
    }

    public String getReCaptchaToken() {
        return reCaptchaToken;
    }

    public void setReCaptchaToken(String reCaptchaToken) {
        this.reCaptchaToken = reCaptchaToken;
    }
}
