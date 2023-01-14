package pl.pja.edu.KDF.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.pja.edu.KDF.DTO.ReservationDTO;
import pl.pja.edu.KDF.Domain.Reservation;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ReservationMapper extends EntityMapper<ReservationDTO, Reservation> {
}
