package pl.pja.edu.KDF.Controller;

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
import pl.pja.edu.KDF.DTO.PersonCreateDTO;
import pl.pja.edu.KDF.DTO.PersonDTO;
import pl.pja.edu.KDF.Exceptions.BadRequestAlertException;
import pl.pja.edu.KDF.Repository.PersonRepository;
import pl.pja.edu.KDF.Service.PersonService;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PersonController {
    
    Logger log = LoggerFactory.getLogger(PersonController.class);
    
    private static final String ENTITY_NAME = "Person";

    private final PersonRepository personRepository;

    private final PersonService personService;

    public PersonController(PersonRepository personRepository, PersonService personService) {
        this.personRepository = personRepository;
        this.personService = personService;
    }
    

    /**
     * {@code POST  /person} : Create a new person.
     *
     * @param personCreateDTO the personCreateDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new personDTO, or with status {@code 400 (Bad Request)} if the person has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/person")
    public ResponseEntity<PersonDTO> createPerson(@Valid @RequestBody PersonCreateDTO personCreateDTO) throws URISyntaxException {
        log.debug("REST request to save person : {}", personCreateDTO);

        PersonDTO result = personService.save(personCreateDTO);
        return ResponseEntity
                .created(new URI("/api/person/" + result.getId()))
                .body(result);
    }

    /**
     * {@code PUT  /person/:id} : Updates an existing person.
     *
     * @param id the id of the personDTO to save.
     * @param personDTO the personDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated personDTO,
     * or with status {@code 400 (Bad Request)} if the personDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the personDTO couldn't be updated.
     */
    @PutMapping("/person/{id}")
    public ResponseEntity<PersonDTO> updatePerson(
            @PathVariable(value = "id", required = false) final Long id,
            @Valid @RequestBody PersonDTO personDTO
    ) {
        log.debug("REST request to update person : {}, {}", id, personDTO);
        if (personDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, personDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!personRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PersonDTO result = personService.update(personDTO);
        return ResponseEntity
                .ok()
                .body(result);
    }

    /**
     * {@code GET  /person} : get all the persons.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of authorities in body.
     */
    @GetMapping("/person")
    public ResponseEntity<List<PersonDTO>> getAllPersons(Pageable pageable){
        log.debug("REST request to get all Authorities");
        Page<PersonDTO> page = personService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /person/:id} : get the "id" person.
     *
     * @param id the id of the personDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the personDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/person/{id}")
    public ResponseEntity<PersonDTO> getPersonDTO(@PathVariable Long id) {
        log.debug("REST request to get person : {}", id);
        Optional<PersonDTO> personDTO = personService.findOne(id);
        return ResponseUtil.wrapOrNotFound(personDTO);
    }

    @DeleteMapping("/person/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id){
        try {
            personService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
        }
    }
}
