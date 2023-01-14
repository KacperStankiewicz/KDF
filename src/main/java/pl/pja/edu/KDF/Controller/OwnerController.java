package pl.pja.edu.KDF.Controller;


import pl.pja.edu.KDF.Controller.Utils.PaginationUtil;
import pl.pja.edu.KDF.Controller.Utils.ResponseUtil;
import pl.pja.edu.KDF.DTO.OwnerDTO;
import pl.pja.edu.KDF.Exceptions.BadRequestAlertException;
import pl.pja.edu.KDF.Repository.OwnerRepository;
import pl.pja.edu.KDF.Service.OwnerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class OwnerController {

    Logger log = LoggerFactory.getLogger(OwnerController.class);

    private static final String ENTITY_NAME = "Owner";

    private final OwnerRepository ownerRepository;

    private final OwnerService ownerService;

    public OwnerController(OwnerRepository ownerRepository, OwnerService ownerService) {
        this.ownerRepository = ownerRepository;
        this.ownerService = ownerService;
    }


    /**
     * {@code POST  /owner} : Create a new owner.
     *
     * @param ownerDTO the ownerDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ownerDTO, or with status {@code 400 (Bad Request)} if the owner has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/owner")
    public ResponseEntity<OwnerDTO> createowner(@Valid @RequestBody OwnerDTO ownerDTO) throws URISyntaxException {
        log.debug("REST request to save owner : {}", ownerDTO);
        if (ownerDTO.getId() != null) {
            throw new BadRequestAlertException("A new owner cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OwnerDTO result = ownerService.save(ownerDTO);
        return ResponseEntity
                .created(new URI("/api/owner/" + result.getId()))
                .body(result);
    }

    /**
     * {@code PUT  /owner/:id} : Updates an existing owner.
     *
     * @param id the id of the ownerDTO to save.
     * @param ownerDTO the ownerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ownerDTO,
     * or with status {@code 400 (Bad Request)} if the ownerDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ownerDTO couldn't be updated.
     */
    @PutMapping("/owner/{id}")
    public ResponseEntity<OwnerDTO> updateowner(
            @PathVariable(value = "id", required = false) final Long id,
            @Valid @RequestBody OwnerDTO ownerDTO
    ) {
        log.debug("REST request to update owner : {}, {}", id, ownerDTO);
        if (ownerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, ownerDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!ownerRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        OwnerDTO result = ownerService.update(ownerDTO);
        return ResponseEntity
                .ok()
                .body(result);
    }

    /**
     * {@code GET  /owner} : get all the owners.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of authorities in body.
     */
    @GetMapping("/owner")
    public ResponseEntity<List<OwnerDTO>> getAllOwners(Pageable pageable){
        log.debug("REST request to get all Authorities");
        Page<OwnerDTO> page = ownerService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /owner/:id} : get the "id" owner.
     *
     * @param id the id of the ownerDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ownerDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/owner/{id}")
    public ResponseEntity<OwnerDTO> getownerDTO(@PathVariable Long id) {
        log.debug("REST request to get owner : {}", id);
        Optional<OwnerDTO> ownerDTO = ownerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ownerDTO);
    }
}
