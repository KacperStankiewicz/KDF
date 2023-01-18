package pl.pja.edu.KDF.Controller;


import org.springframework.http.HttpStatus;
import pl.pja.edu.KDF.Controller.Utils.PaginationUtil;
import pl.pja.edu.KDF.Controller.Utils.ResponseUtil;
import pl.pja.edu.KDF.DTO.ReservationCreateDTO;
import pl.pja.edu.KDF.DTO.ReservationDTO;
import pl.pja.edu.KDF.Exceptions.BadRequestAlertException;
import pl.pja.edu.KDF.Repository.ReservationRepository;
import pl.pja.edu.KDF.Security.reCaptchaV3.ReCaptchaHandler;
import pl.pja.edu.KDF.Service.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.pja.edu.KDF.Controller.Utils.PaginationUtil;
import pl.pja.edu.KDF.Controller.Utils.ResponseUtil;
import pl.pja.edu.KDF.Controller.error.HeaderUtil;
import pl.pja.edu.KDF.DTO.ReservationDTO;
import pl.pja.edu.KDF.Exceptions.BadRequestAlertException;
import pl.pja.edu.KDF.Repository.ReservationRepository;
import pl.pja.edu.KDF.Service.ReservationService;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@RequestMapping("/api")
@RestController
public class ReservationController {
    Logger log = LoggerFactory.getLogger(ReservationController.class);

    private static final String ENTITY_NAME = "Reservation";

    private final ReservationRepository reservationRepository;

    private final ReservationService reservationService;

    private final ReCaptchaHandler reCaptchaHandler;

    public ReservationController(ReservationRepository reservationRepository, ReservationService reservationService, ReCaptchaHandler reCaptchaHandler) {
        this.reservationRepository = reservationRepository;
        this.reservationService = reservationService;
        this.reCaptchaHandler = reCaptchaHandler;
    }


    /**
     * {@code POST  /reservation} : Create a new reservation.
     *
     * @param reservationCreateDTO the reservationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new reservationDTO, or with status {@code 400 (Bad Request)} if the reservation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/reservation")
    public ResponseEntity<ReservationDTO> createReservation(@Valid @RequestBody ReservationCreateDTO reservationCreateDTO) throws URISyntaxException {
        log.debug("REST request to save reservation : {}", reservationCreateDTO);
        if(reCaptchaHandler.verify(reservationCreateDTO.getReCaptchaToken()) < 0.5f){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (reservationCreateDTO.getReservationDTO().getId() != null) {
            throw new BadRequestAlertException("A new reservation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ReservationDTO result = reservationService.save(reservationCreateDTO.getReservationDTO());
        return ResponseEntity
                .created(new URI("/api/reservation/" + result.getId()))
                .body(result);
    }

    /**
     * {@code PUT  /reservation/:id} : Updates an existing reservation.
     *
     * @param id the id of the reservationDTO to save.
     * @param reservationDTO the reservationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated reservationDTO,
     * or with status {@code 400 (Bad Request)} if the reservationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the reservationDTO couldn't be updated.
     */
    @PutMapping("/reservation/{id}")
    public ResponseEntity<ReservationDTO> updateReservation(
            @PathVariable(value = "id", required = false) final Long id,
            @Valid @RequestBody ReservationDTO reservationDTO
    ) {
        log.debug("REST request to update reservation : {}, {}", id, reservationDTO);
        if (reservationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, reservationDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!reservationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ReservationDTO result = reservationService.update(reservationDTO);
        return ResponseEntity
                .ok()
                .body(result);
    }

    /**
     * {@code GET  /reservation} : get all the reservations.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of authorities in body.
     */
    @GetMapping("/reservation")
    public ResponseEntity<List<ReservationDTO>> getAllReservations(Pageable pageable){
        log.debug("REST request to get all Authorities");
        Page<ReservationDTO> page = reservationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /reservation/:id} : get the "id" reservation.
     *
     * @param id the id of the reservationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the reservationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/reservation/{id}")
    public ResponseEntity<ReservationDTO> getReservationDTO(@PathVariable Long id) {
        log.debug("REST request to get reservation : {}", id);
        Optional<ReservationDTO> reservationDTO = reservationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(reservationDTO);
    }

    @PutMapping("/reservation/{id}/delete/soft")
    public ResponseEntity<ReservationDTO> softDeleteReservation(
            @PathVariable(value = "id", required = false) final Long id,
            @Valid @RequestBody ReservationDTO reservationDTO) {
        log.debug("REST request to update Reservation : {}, {}", id, reservationDTO);
        if (reservationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, reservationDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!reservationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ReservationDTO result = reservationService.softDelete(reservationDTO);
        return ResponseEntity
                .ok()
                .headers(HeaderUtil.createEntityUpdateAlert("KDF", false, ENTITY_NAME, reservationDTO.getId().toString()))
                .body(result);
    }

    @DeleteMapping("/reservation/{id}/delete")
    public ResponseEntity<Void> deleteReservation(@PathVariable(value = "id", required = true) Long id){
        if (!reservationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        reservationService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
