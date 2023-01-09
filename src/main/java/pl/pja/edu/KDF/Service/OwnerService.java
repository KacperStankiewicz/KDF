package pl.pja.edu.KDF.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.pja.edu.KDF.DTO.OwnerDTO;
import pl.pja.edu.KDF.Domain.Owner;
import pl.pja.edu.KDF.Mapper.OwnerMapper;
import pl.pja.edu.KDF.Repository.OwnerRepository;

import java.util.Optional;

@Service
public class OwnerService {

    private final Logger log = LoggerFactory.getLogger(OwnerService.class);

    private final OwnerMapper ownerMapper;

    private final OwnerRepository ownerRepository;

    public OwnerService(OwnerMapper ownerMapper, OwnerRepository ownerRepository) {
        this.ownerMapper = ownerMapper;
        this.ownerRepository = ownerRepository;
    }


    /**
     * Save an Owner.
     *
     * @param ownerDTO the entity to save.
     * @return the persisted entity.
     */
    public OwnerDTO save(OwnerDTO ownerDTO) {
        log.debug("Request to save Owner : {}", ownerDTO);
        Owner owner = ownerMapper.toEntity(ownerDTO);
        owner = ownerRepository.save(owner);
        return ownerMapper.toDto(owner);
    }

    /**
     * Update an Owner.
     *
     * @param ownerDTO the entity to save.
     * @return the persisted entity.
     */
    public OwnerDTO update(OwnerDTO ownerDTO) {
        log.debug("Request to update Owner : {}", ownerDTO);
        Owner owner = ownerMapper.toEntity(ownerDTO);
        owner = ownerRepository.save(owner);
        return ownerMapper.toDto(owner);
    }

    /**
     * Partially update an Owner.
     *
     * @param ownerDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<OwnerDTO> partialUpdate(OwnerDTO ownerDTO) {
        log.debug("Request to partially update Owner : {}", ownerDTO);

        return ownerRepository
                .findById(ownerDTO.getId())
                .map(existingOwner -> {
                    ownerMapper.partialUpdate(existingOwner, ownerDTO);

                    return existingOwner;
                })
                .map(ownerRepository::save)
                .map(ownerMapper::toDto);
    }

    /**
     * Get all the authorities.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<OwnerDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Owneres");
        return ownerRepository.findAll(pageable).map(ownerMapper::toDto);
    }

    /**
     * Get one owner by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<OwnerDTO> findOne(Long id) {
        log.debug("Request to get PriceList : {}", id);
        return ownerRepository.findById(id).map(ownerMapper::toDto);
    }
}
