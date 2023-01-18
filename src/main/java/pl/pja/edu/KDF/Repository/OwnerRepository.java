package pl.pja.edu.KDF.Repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pja.edu.KDF.Domain.Owner;

import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    @EntityGraph(attributePaths = { "authorities" })
    Optional<Owner> findOneWithAuthoritiesByEmailIgnoreCase(String email);

    @EntityGraph(attributePaths = { "authorities" })
    Optional<Owner> findOneWithAuthoritiesByLogin(String login);
}