package pl.pja.edu.KDF.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.pja.edu.KDF.DTO.StationDTO;
import pl.pja.edu.KDF.Domain.Station;
import pl.pja.edu.KDF.Mapper.StationMapper;
import pl.pja.edu.KDF.Repository.StationRepository;

import java.util.Optional;

@Service
public class StationService {
    private final Logger log = LoggerFactory.getLogger(StationService.class);

    private final StationRepository stationRepository;

    private final StationMapper stationMapper;

    public StationService(StationRepository stationRepository, StationMapper stationMapper) {
        this.stationRepository = stationRepository;
        this.stationMapper = stationMapper;
    }


    /**
     * Save a Station.
     *
     * @param stationDTO the entity to save.
     * @return the persisted entity.
     */
    public StationDTO save(StationDTO stationDTO) {
        log.debug("Request to save Owner : {}", stationDTO);
        Station station = stationMapper.toEntity(stationDTO);
        station = stationRepository.save(station);
        return stationMapper.toDto(station);
    }

    /**
     * Update a station.
     *
     * @param stationDTO the entity to save.
     * @return the persisted entity.
     */
    public StationDTO update(StationDTO stationDTO) {
        log.debug("Request to update Owner : {}", stationDTO);
        Station station = stationMapper.toEntity(stationDTO);
        station = stationRepository.save(station);
        return stationMapper.toDto(station);
    }

    /**
     * Partially update a station.
     *
     * @param stationDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<StationDTO> partialUpdate(StationDTO stationDTO) {
        log.debug("Request to partially update Owner : {}", stationDTO);

        return stationRepository
                .findById(stationDTO.getId())
                .map(existingstation -> {
                    stationMapper.partialUpdate(existingstation, stationDTO);

                    return existingstation;
                })
                .map(stationRepository::save)
                .map(stationMapper::toDto);
    }

    /**
     * Get all the stations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<StationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Stations");
        return stationRepository.findAll(pageable).map(stationMapper::toDto);
    }


    /**
     * Get one station by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<StationDTO> findOne(Long id) {
        log.debug("Request to get PriceList : {}", id);
        return stationRepository.findById(id).map(stationMapper::toDto);
    }
}
