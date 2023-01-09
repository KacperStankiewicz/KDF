package pl.pja.edu.KDF.Mapper;

import pl.pja.edu.KDF.Domain.Object;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.pja.edu.KDF.DTO.ObjectDTO;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ObjectMapper extends EntityMapper<ObjectDTO, Object> {
}
