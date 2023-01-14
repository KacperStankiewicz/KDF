package pl.pja.edu.KDF.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.pja.edu.KDF.DTO.ObjectDTO;
import pl.pja.edu.KDF.Domain.Object;
import pl.pja.edu.KDF.Mapper.ObjectMapper;
import pl.pja.edu.KDF.Repository.ObjectRepository;

import java.util.Optional;

@Service
public class ObjectService {
    Logger log = LoggerFactory.getLogger(ObjectService.class);

    private final ObjectMapper objectMapper;

    private final ObjectRepository objectRepository;

    public ObjectService(ObjectMapper objectMapper, ObjectRepository objectRepository) {
        this.objectMapper = objectMapper;
        this.objectRepository = objectRepository;
    }


    /**
     * Save an Object.
     *
     * @param objectDTO the entity to save.
     * @return the persisted entity.
     */
    public ObjectDTO save(ObjectDTO objectDTO) {
        log.debug("Request to save Object : {}", objectDTO);
        Object object = objectMapper.toEntity(objectDTO);
        object = objectRepository.save(object);
        return objectMapper.toDto(object);
    }

    /**
     * Update an Object.
     *
     * @param objectDTO the entity to save.
     * @return the persisted entity.
     */
    public ObjectDTO update(ObjectDTO objectDTO) {
        log.debug("Request to update Object : {}", objectDTO);
        Object object = objectMapper.toEntity(objectDTO);
        object = objectRepository.save(object);
        return objectMapper.toDto(object);
    }

    /**
     * Partially update an Object.
     *
     * @param objectDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ObjectDTO> partialUpdate(ObjectDTO objectDTO) {
        log.debug("Request to partially update Object : {}", objectDTO);

        return objectRepository
                .findById(objectDTO.getId())
                .map(existingObject -> {
                    objectMapper.partialUpdate(existingObject, objectDTO);

                    return existingObject;
                })
                .map(objectRepository::save)
                .map(objectMapper::toDto);
    }

    /**
     * Get all the authorities.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ObjectDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Objectes");
        return objectRepository.findAll(pageable).map(objectMapper::toDto);
    }

    /**
     * Get one object by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ObjectDTO> findOne(Long id) {
        log.debug("Request to get PriceList : {}", id);
        return objectRepository.findById(id).map(objectMapper::toDto);
    }
}
