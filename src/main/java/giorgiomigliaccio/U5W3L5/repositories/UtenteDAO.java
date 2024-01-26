package giorgiomigliaccio.U5W3L5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import giorgiomigliaccio.U5W3L5.entities.Utente;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UtenteDAO extends JpaRepository<Utente, UUID> {
    Optional<Utente> findByEmail(String email);
}
