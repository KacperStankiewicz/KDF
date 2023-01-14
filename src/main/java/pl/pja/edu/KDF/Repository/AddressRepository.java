package pl.pja.edu.KDF.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pja.edu.KDF.Domain.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
}
