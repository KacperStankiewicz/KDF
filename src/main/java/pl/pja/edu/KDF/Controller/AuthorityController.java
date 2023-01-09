package pl.pja.edu.KDF.Controller;


import pl.pja.edu.KDF.Controller.Utils.PaginationUtil;
import pl.pja.edu.KDF.Controller.Utils.ResponseUtil;
import pl.pja.edu.KDF.DTO.AuthorityDTO;
import pl.pja.edu.KDF.Exceptions.BadRequestAlertException;
import pl.pja.edu.KDF.Repository.AuthorityRepository;
import pl.pja.edu.KDF.Service.AuthorityService;
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
public class AuthorityController {

    Logger log = LoggerFactory.getLogger(AuthorityController.class);

    private static final String ENTITY_NAME = "Authority";

    private final AuthorityRepository authorityRepository;

    private final AuthorityService authorityService;

    public AuthorityController(AuthorityRepository authorityRepository, AuthorityService authorityService) {
        this.authorityRepository = authorityRepository;
        this.authorityService = authorityService;
    }

    /**
     * {@code POST  /authority} : Create a new authority.
     *
     * @param authorityDTO the addressDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new authorityDTO, or with status {@code 400 (Bad Request)} if the authority has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/authority")
    public ResponseEntity<AuthorityDTO> createAuthority(@Valid @RequestBody AuthorityDTO authorityDTO) throws URISyntaxException {
        log.debug("REST request to save Authority : {}", authorityDTO);
        if (authorityDTO.getId() != null) {
            throw new BadRequestAlertException("A new authority cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AuthorityDTO result = authorityService.save(authorityDTO);
        return ResponseEntity
                .created(new URI("/api/authority/" + result.getId()))
                .body(result);
    }

    /**
     * {@code PUT  /authority/:id} : Updates an existing authority.
     *
     * @param id the id of the authorityDTO to save.
     * @param authorityDTO the authorityDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated authorityDTO,
     * or with status {@code 400 (Bad Request)} if the authorityDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the authorityDTO couldn't be updated.
     */
    @PutMapping("/authority/{id}")
    public ResponseEntity<AuthorityDTO> updateAuthority(
            @PathVariable(value = "id", required = false) final Long id,
            @Valid @RequestBody AuthorityDTO authorityDTO
    ) {
        log.debug("REST request to update Authority : {}, {}", id, authorityDTO);
        if (authorityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, authorityDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!authorityRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AuthorityDTO result = authorityService.update(authorityDTO);
        return ResponseEntity
                .ok()
                .body(result);
    }

    /**
     * {@code GET  /authority} : get all the authorities.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of authorities in body.
     */
    @GetMapping("/authority")
    public ResponseEntity<List<AuthorityDTO>> getAllAuthorities(Pageable pageable){
        log.debug("REST request to get all Authorities");
        Page<AuthorityDTO> page = authorityService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /authority/:id} : get the "id" authority.
     *
     * @param id the id of the authorityDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the authorityDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/authority/{id}")
    public ResponseEntity<AuthorityDTO> getAuthorityDTO(@PathVariable Long id) {
        log.debug("REST request to get Authority : {}", id);
        Optional<AuthorityDTO> authorityDTO = authorityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(authorityDTO);
    }
}
