package pl.pja.edu.KDF.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.pja.edu.KDF.DTO.AuthorityDTO;
import pl.pja.edu.KDF.Domain.Authority;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AuthorityMapper extends EntityMapper<AuthorityDTO, Authority> {
}
