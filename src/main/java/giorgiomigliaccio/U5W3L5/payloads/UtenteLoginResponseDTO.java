package giorgiomigliaccio.U5W3L5.payloads;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import giorgiomigliaccio.U5W3L5.entities.Utente;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UtenteLoginResponseDTO extends JpaRepository<Utente, UUID> {
    Optional<Utente> findByEmail(String email);
}
