package pl.pja.edu.KDF.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.pja.edu.KDF.DTO.PersonCreateDTO;
import pl.pja.edu.KDF.DTO.PersonDTO;
import pl.pja.edu.KDF.Domain.Person;
import pl.pja.edu.KDF.Exceptions.BadRequestAlertException;
import pl.pja.edu.KDF.Mapper.PersonMapper;
import pl.pja.edu.KDF.Repository.ObjectRepository;
import pl.pja.edu.KDF.Repository.PersonRepository;

import java.util.Optional;

@Service
public class PersonService {

    private final Logger log = LoggerFactory.getLogger(PersonService.class);

    private final PersonMapper personMapper;

    private final PersonRepository personRepository;


    private final AddressService addressService;
    private final ObjectRepository objectRepository;

    public PersonService(PersonMapper personMapper, PersonRepository personRepository, AddressService addressService,
                         ObjectRepository objectRepository) {
        this.personMapper = personMapper;
        this.personRepository = personRepository;
        this.addressService = addressService;
        this.objectRepository = objectRepository;
    }

    /**
     * Save a Person.
     *
     * @param personDTO the entity to save.
     * @return the persisted entity.
     */
    public PersonDTO save(PersonDTO personDTO) {
        log.debug("Request to save Person : {}", personDTO);
        Person person = personMapper.toEntity(personDTO);
        person = personRepository.save(person);
        return personMapper.toDto(person);
    }

    /**
     * Save a Person.
     *
     * @param personCreateDTO the entity to save.
     * @return the persisted entity.
     */
    public PersonDTO save(PersonCreateDTO personCreateDTO) {
        log.debug("Request to save Person : {}", personCreateDTO);
        personCreateDTO.setAddress(addressService.save(personCreateDTO.getAddress()));
        Person person = personMapper.personCreateDTOtoPerson(personCreateDTO);
        person = personRepository.save(person);

        person.setObject(objectRepository.findById(personCreateDTO.getObjectId())
                .orElseThrow(() ->
                        new BadRequestAlertException(
                                "Object with id " + personCreateDTO.getObjectId() + " does not exist",
                                "Object",
                                "iddoesnotexist")
                )
        );
        return personMapper.toDto(person);
    }

    /**
     * Update an Owner.
     *
     * @param personDTO the entity to save.
     * @return the persisted entity.
     */
    public PersonDTO update(PersonDTO personDTO) {
        log.debug("Request to update Owner : {}", personDTO);
        Person person = personMapper.toEntity(personDTO);
        person = personRepository.save(person);
        return personMapper.toDto(person);
    }

    /**
     * Partially update a Person.
     *
     * @param personDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<PersonDTO> partialUpdate(PersonDTO personDTO) {
        log.debug("Request to partially update Owner : {}", personDTO);

        return personRepository
                .findById(personDTO.getId())
                .map(existingPerson -> {
                    personMapper.partialUpdate(existingPerson, personDTO);

                    return existingPerson;
                })
                .map(personRepository::save)
                .map(personMapper::toDto);
    }

    /**
     * Get all the persons.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<PersonDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Owneres");
        return personRepository.findAll(pageable).map(personMapper::toDto);
    }


    /**
     * Get one owner by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PersonDTO> findOne(Long id) {
        log.debug("Request to get PriceList : {}", id);
        return personRepository.findById(id).map(personMapper::toDto);
    }

    @Transactional
    public void delete(Long id){
        personRepository.deleteById(id);
    }
}
