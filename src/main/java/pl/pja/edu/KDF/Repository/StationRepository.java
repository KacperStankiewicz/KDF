package pl.pja.edu.KDF.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pja.edu.KDF.Domain.Station;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {
}