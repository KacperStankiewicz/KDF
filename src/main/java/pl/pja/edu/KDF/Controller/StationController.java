package pl.pja.edu.KDF.Controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.pja.edu.KDF.Controller.Utils.PaginationUtil;
import pl.pja.edu.KDF.Controller.Utils.ResponseUtil;
import pl.pja.edu.KDF.DTO.StationDTO;
import pl.pja.edu.KDF.Exceptions.BadRequestAlertException;
import pl.pja.edu.KDF.Repository.StationRepository;
import pl.pja.edu.KDF.Service.StationService;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class StationController {
    Logger log = LoggerFactory.getLogger(StationController.class);

    private static final String ENTITY_NAME = "Station";

    private final StationRepository stationRepository;

    private final StationService stationService;

    public StationController(StationRepository stationRepository, StationService stationService) {
        this.stationRepository = stationRepository;
        this.stationService = stationService;
    }


    /**
     * {@code POST  /station} : Create a new station.
     *
     * @param stationDTO the stationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new stationDTO, or with status {@code 400 (Bad Request)} if the station has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/station")
    public ResponseEntity<StationDTO> createStation(@Valid @RequestBody StationDTO stationDTO) throws URISyntaxException {
        log.debug("REST request to save station : {}", stationDTO);
        if (stationDTO.getId() != null) {
            throw new BadRequestAlertException("A new station cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StationDTO result = stationService.save(stationDTO);
        return ResponseEntity
                .created(new URI("/api/station/" + result.getId()))
                .body(result);
    }

    /**
     * {@code PUT  /station/:id} : Updates an existing station.
     *
     * @param id the id of the stationDTO to save.
     * @param stationDTO the stationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated stationDTO,
     * or with status {@code 400 (Bad Request)} if the stationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the stationDTO couldn't be updated.
     */
    @PutMapping("/station/{id}")
    public ResponseEntity<StationDTO> updateStation(
            @PathVariable(value = "id", required = false) final Long id,
            @Valid @RequestBody StationDTO stationDTO
    ) {
        log.debug("REST request to update station : {}, {}", id, stationDTO);
        if (stationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, stationDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!stationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        StationDTO result = stationService.update(stationDTO);
        return ResponseEntity
                .ok()
                .body(result);
    }

    /**
     * {@code GET  /station} : get all the stations.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of authorities in body.
     */
    @GetMapping("/station")
    public ResponseEntity<List<StationDTO>> getAllStations(Pageable pageable){
        log.debug("REST request to get all Authorities");
        Page<StationDTO> page = stationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /station/:id} : get the "id" station.
     *
     * @param id the id of the stationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the stationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/station/{id}")
    public ResponseEntity<StationDTO> getStationDTO(@PathVariable Long id) {
        log.debug("REST request to get station : {}", id);
        Optional<StationDTO> stationDTO = stationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(stationDTO);
    }
}
