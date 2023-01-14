package pl.pja.edu.KDF.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.pja.edu.KDF.DTO.OwnerDTO;
import pl.pja.edu.KDF.Domain.Owner;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface OwnerMapper extends EntityMapper<OwnerDTO, Owner> {
}
