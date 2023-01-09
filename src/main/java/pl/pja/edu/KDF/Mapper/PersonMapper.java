package pl.pja.edu.KDF.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.pja.edu.KDF.DTO.PersonDTO;
import pl.pja.edu.KDF.Domain.Person;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface PersonMapper extends EntityMapper<PersonDTO, Person> {
}
