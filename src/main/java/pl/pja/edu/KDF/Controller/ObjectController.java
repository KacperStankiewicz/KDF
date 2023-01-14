package pl.pja.edu.KDF.Controller;

import pl.pja.edu.KDF.Controller.Utils.PaginationUtil;
import pl.pja.edu.KDF.Controller.Utils.ResponseUtil;
import pl.pja.edu.KDF.DTO.ObjectDTO;
import pl.pja.edu.KDF.Exceptions.BadRequestAlertException;
import pl.pja.edu.KDF.Repository.ObjectRepository;
import pl.pja.edu.KDF.Service.ObjectService;
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
public class ObjectController {

    Logger log = LoggerFactory.getLogger(ObjectController.class);

    private static final String ENTITY_NAME = "Object";

    private final ObjectRepository objectRepository;

    private final ObjectService objectService;

    public ObjectController(ObjectRepository objectRepository, ObjectService objectService) {
        this.objectRepository = objectRepository;
        this.objectService = objectService;
    }


    /**
     * {@code POST  /object} : Create a new object.
     *
     * @param objectDTO the objectDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new objectDTO, or with status {@code 400 (Bad Request)} if the object has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/object")
    public ResponseEntity<ObjectDTO> createobject(@Valid @RequestBody ObjectDTO objectDTO) throws URISyntaxException {
        log.debug("REST request to save object : {}", objectDTO);
        if (objectDTO.getId() != null) {
            throw new BadRequestAlertException("A new object cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ObjectDTO result = objectService.save(objectDTO);
        return ResponseEntity
                .created(new URI("/api/object/" + result.getId()))
                .body(result);
    }

    /**
     * {@code PUT  /object/:id} : Updates an existing object.
     *
     * @param id the id of the objectDTO to save.
     * @param objectDTO the objectDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated objectDTO,
     * or with status {@code 400 (Bad Request)} if the objectDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the objectDTO couldn't be updated.
     */
    @PutMapping("/object/{id}")
    public ResponseEntity<ObjectDTO> updateobject(
            @PathVariable(value = "id", required = false) final Long id,
            @Valid @RequestBody ObjectDTO objectDTO
    ) {
        log.debug("REST request to update object : {}, {}", id, objectDTO);
        if (objectDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, objectDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!objectRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ObjectDTO result = objectService.update(objectDTO);
        return ResponseEntity
                .ok()
                .body(result);
    }

    /**
     * {@code GET  /object} : get all the objects.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of authorities in body.
     */
    @GetMapping("/object")
    public ResponseEntity<List<ObjectDTO>> getAllObjects(Pageable pageable){
        log.debug("REST request to get all Authorities");
        Page<ObjectDTO> page = objectService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /object/:id} : get the "id" object.
     *
     * @param id the id of the objectDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the objectDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/object/{id}")
    public ResponseEntity<ObjectDTO> getobjectDTO(@PathVariable Long id) {
        log.debug("REST request to get object : {}", id);
        Optional<ObjectDTO> objectDTO = objectService.findOne(id);
        return ResponseUtil.wrapOrNotFound(objectDTO);
    }
}
