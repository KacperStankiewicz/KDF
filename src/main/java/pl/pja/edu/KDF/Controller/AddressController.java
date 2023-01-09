package pl.pja.edu.KDF.Controller;

import pl.pja.edu.KDF.Controller.Utils.PaginationUtil;
import pl.pja.edu.KDF.Controller.Utils.ResponseUtil;
import pl.pja.edu.KDF.DTO.AddressDTO;
import pl.pja.edu.KDF.Exceptions.BadRequestAlertException;
import pl.pja.edu.KDF.Repository.AddressRepository;
import pl.pja.edu.KDF.Service.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AddressController {

    private final Logger log = LoggerFactory.getLogger(AddressController.class);

    private static final String ENTITY_NAME = "Address";

    private final AddressService addressService;

    private final AddressRepository addressRepository;

    public AddressController(AddressService addressService, AddressRepository addressRepository) {
        this.addressService = addressService;
        this.addressRepository = addressRepository;
    }

    /**
     * {@code POST  /addresses} : Create a new address.
     *
     * @param addressDTO the addressDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new addressDTO, or with status {@code 400 (Bad Request)} if the address has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/addresses")
    public ResponseEntity<AddressDTO> createAddress(@RequestBody AddressDTO addressDTO) throws URISyntaxException {
        log.debug("REST request to save Address : {}", addressDTO);
        if (addressDTO.getId() != null) {
            throw new BadRequestAlertException("A new address cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AddressDTO result = addressService.save(addressDTO);
        return ResponseEntity
                .created(new URI("/api/addresses/" + result.getId()))
                .body(result);
    }

    /**
     * {@code PUT  /addresses/:id} : Updates an existing address.
     *
     * @param id         the id of the addressDTO to save.
     * @param addressDTO the addressDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated addressDTO,
     * or with status {@code 400 (Bad Request)} if the addressDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the addressDTO couldn't be updated.
     */
    @PutMapping("/addresses/{id}")
    public ResponseEntity<AddressDTO> updateAddress(
            @PathVariable(value = "id", required = false) final Long id,
            @RequestBody AddressDTO addressDTO
    ) {
        log.debug("REST request to update Address : {}, {}", id, addressDTO);
        if (addressDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, addressDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!addressRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AddressDTO result = addressService.update(addressDTO);
        return ResponseEntity
                .ok()
                .body(result);
    }

    /**
     * {@code GET  /addresses} : get all the addresses.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of addresses in body.
     */
    @GetMapping("/addresses")
    public ResponseEntity<List<AddressDTO>> getAllAddresses(Pageable pageable) {
        log.debug("REST request to get all Addresses");
        Page<AddressDTO> page = addressService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /addresses/:id} : get the "id" address.
     *
     * @param id the id of the addressDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the addressDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/addresses/{id}")
    public ResponseEntity<AddressDTO> getAddressDTO(@PathVariable Long id) {
        log.debug("REST request to get PriceList : {}", id);
        Optional<AddressDTO> addressDTO = addressService.findOne(id);
        return ResponseUtil.wrapOrNotFound(addressDTO);
    }

}
