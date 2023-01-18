package pl.pja.edu.KDF.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.pja.edu.KDF.DTO.ReservationDTO;
import pl.pja.edu.KDF.Domain.Reservation;
import pl.pja.edu.KDF.Domain.Station;
import pl.pja.edu.KDF.Enumeration.StationStatus;
import pl.pja.edu.KDF.Mapper.ReservationMapper;
import pl.pja.edu.KDF.Repository.ReservationRepository;
import pl.pja.edu.KDF.Repository.StationRepository;

import java.util.Optional;

@Service
public class ReservationService {

    private final Logger log = LoggerFactory.getLogger(ReservationService.class);

    private final ReservationMapper reservationMapper;

    private final ReservationRepository reservationRepository;

    private final StationRepository stationRepository;


    public ReservationService(ReservationMapper reservationMapper, ReservationRepository reservationRepository, StationRepository stationRepository) {
        this.reservationMapper = reservationMapper;
        this.reservationRepository = reservationRepository;
        this.stationRepository = stationRepository;
    }

    /**
     * Save a Reservation.
     *
     * @param reservationDTO the entity to save.
     * @return the persisted entity.
     */
    public ReservationDTO save(ReservationDTO reservationDTO) {
        log.debug("Request to save Owner : {}", reservationDTO);
        Reservation reservation = reservationMapper.toEntity(reservationDTO);
        Station station = stationRepository.findByStatusIsAndObject_Id(StationStatus.FREE, 1L).get();
        reservation.setStation(station);

        reservation = reservationRepository.save(reservation);
        return reservationMapper.toDto(reservation);
    }

    /**
     * Update a Reservation.
     *
     * @param reservationDTO the entity to save.
     * @return the persisted entity.
     */
    public ReservationDTO update(ReservationDTO reservationDTO) {
        log.debug("Request to update Owner : {}", reservationDTO);
        Reservation reservation = reservationMapper.toEntity(reservationDTO);
        reservation = reservationRepository.save(reservation);
        return reservationMapper.toDto(reservation);
    }

    /**
     * Partially update a Reservation.
     *
     * @param reservationDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ReservationDTO> partialUpdate(ReservationDTO reservationDTO) {
        log.debug("Request to partially update Owner : {}", reservationDTO);

        return reservationRepository
                .findById(reservationDTO.getId())
                .map(existingReservation -> {
                    reservationMapper.partialUpdate(existingReservation, reservationDTO);

                    return existingReservation;
                })
                .map(reservationRepository::save)
                .map(reservationMapper::toDto);
    }

    /**
     * Get all the reservations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ReservationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Owneres");
        return reservationRepository.findAll(pageable).map(reservationMapper::toDto);
    }


    /**
     * Get one reservation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ReservationDTO> findOne(Long id) {
        log.debug("Request to get PriceList : {}", id);
        return reservationRepository.findById(id).map(reservationMapper::toDto);
    }
}
