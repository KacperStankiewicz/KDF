package pl.pja.edu.KDF.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.pja.edu.KDF.DTO.AuthorityDTO;
import pl.pja.edu.KDF.Domain.Authority;
import pl.pja.edu.KDF.Mapper.AuthorityMapper;
import pl.pja.edu.KDF.Repository.AuthorityRepository;

import java.util.Optional;

@Service
public class AuthorityService {
    Logger log = LoggerFactory.getLogger(AuthorityService.class);

    private final AuthorityMapper authorityMapper;

    private final AuthorityRepository authorityRepository;

    public AuthorityService(AuthorityMapper authorityMapper, AuthorityRepository authorityRepository) {
        this.authorityMapper = authorityMapper;
        this.authorityRepository = authorityRepository;
    }


    /**
     * Save an Authority.
     *
     * @param authorityDTO the entity to save.
     * @return the persisted entity.
     */
    public AuthorityDTO save(AuthorityDTO authorityDTO) {
        log.debug("Request to save Authority : {}", authorityDTO);
        Authority authority = authorityMapper.toEntity(authorityDTO);
        authority = authorityRepository.save(authority);
        return authorityMapper.toDto(authority);
    }

    /**
     * Update an Authority.
     *
     * @param authorityDTO the entity to save.
     * @return the persisted entity.
     */
    public AuthorityDTO update(AuthorityDTO authorityDTO) {
        log.debug("Request to update Authority : {}", authorityDTO);
        Authority authority = authorityMapper.toEntity(authorityDTO);
        authority = authorityRepository.save(authority);
        return authorityMapper.toDto(authority);
    }

    /**
     * Partially update an Authority.
     *
     * @param authorityDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<AuthorityDTO> partialUpdate(AuthorityDTO authorityDTO) {
        log.debug("Request to partially update Authority : {}", authorityDTO);

        return authorityRepository
                .findById(authorityDTO.getId())
                .map(existingAuthority -> {
                    authorityMapper.partialUpdate(existingAuthority, authorityDTO);

                    return existingAuthority;
                })
                .map(authorityRepository::save)
                .map(authorityMapper::toDto);
    }

    /**
     * Get all the authorities.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<AuthorityDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Authorityes");
        return authorityRepository.findAll(pageable).map(authorityMapper::toDto);
    }

    /**
     * Get one authority by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AuthorityDTO> findOne(Long id) {
        log.debug("Request to get PriceList : {}", id);
        return authorityRepository.findById(id).map(authorityMapper::toDto);
    }
}
