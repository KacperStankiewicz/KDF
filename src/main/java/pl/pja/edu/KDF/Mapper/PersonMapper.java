package pl.pja.edu.KDF.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import pl.pja.edu.KDF.DTO.PersonCreateDTO;
import pl.pja.edu.KDF.DTO.PersonDTO;
import pl.pja.edu.KDF.Domain.Authority;
import pl.pja.edu.KDF.Domain.Object;
import pl.pja.edu.KDF.Domain.Person;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", uses = AddressMapper.class)
public abstract class PersonMapper implements EntityMapper<PersonDTO, Person> {


    @Autowired
    protected AddressMapper addressMapper;


    public Person personCreateDTOtoPerson(PersonCreateDTO personCreateDTO){
        return Person.builder()
                .firstname(personCreateDTO.getFirstname())
                .lastname(personCreateDTO.getLastname())
                .phone(personCreateDTO.getPhone())
                .email(personCreateDTO.getEmail())
                .authorities(List.of(Authority.builder().name("ROLE_WORKER").build()))
                .address(addressMapper.toEntity(personCreateDTO.getAddress()))
                .object(Object.builder().id(personCreateDTO.getObjectId()).build())
                .build();
    }
}
