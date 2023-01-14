package pl.pja.edu.KDF.Mapper;

import org.mapstruct.Mapper;
import pl.pja.edu.KDF.DTO.AddressDTO;
import pl.pja.edu.KDF.Domain.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper extends EntityMapper<AddressDTO,Address>{

}