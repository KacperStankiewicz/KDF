package pl.pja.edu.KDF.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pja.edu.KDF.DTO.ReservationDTO;
import pl.pja.edu.KDF.Exceptions.BadRequestAlertException;
import pl.pja.edu.KDF.Service.ReservationService;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api")
@Slf4j
public class WorkerController {

    private static final String ENTITY_NAME = "Reservation";


    private final ReservationService reservationService;

    public WorkerController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }


    /**
     * {@code POST  /reservation} : Create a new reservation.
     *
     * @param reservationDTO the reservationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new reservationDTO, or with status {@code 400 (Bad Request)} if the reservation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/worker/reservation")
    public ResponseEntity<ReservationDTO> createReservation(@Valid @RequestBody ReservationDTO reservationDTO) throws URISyntaxException {
        log.debug("REST request to save reservation : {}", reservationDTO);

        if (reservationDTO.getId() != null) {
            throw new BadRequestAlertException("A new reservation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ReservationDTO result = reservationService.save(reservationDTO);
        return ResponseEntity
                .created(new URI("/api/reservation/" + result.getId()))
                .body(result);
    }
}
