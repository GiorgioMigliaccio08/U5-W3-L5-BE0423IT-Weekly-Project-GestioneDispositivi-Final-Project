package giorgiomigliaccio.U5W3L5.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import giorgiomigliaccio.U5W3L5.entities.Utente;
import giorgiomigliaccio.U5W3L5.repositories.UtenteDAO;
import giorgiomigliaccio.U5W3L5.exceptions.BadRequestException;
import giorgiomigliaccio.U5W3L5.exceptions.NotFoundException;
import giorgiomigliaccio.U5W3L5.payloads.NewUtenteDTO;


import java.util.UUID;

@Service
public class UtenteService {
    @Autowired
    private UtenteDAO utenteDAO;


    public Page<Utente> getUsers(int page, int size, String orderBy) {

        if (size >= 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return utenteDAO.findAll(pageable);
    }

    public Utente save(NewUtenteDTO body) {
        utenteDAO.findByEmail(body.email()).ifPresent(user -> {
            throw new BadRequestException("L'email " + user.getEmail() + " è già in uso!");
        });

        Utente newUser = new Utente();
        newUser.setSurname(body.surname());
        newUser.setName(body.name());
        newUser.setEmail(body.email());
        newUser.setPassword(body.password());
        return utenteDAO.save(newUser);
    }

    public Utente findById(UUID id) {
        return utenteDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(UUID id) {
        Utente found = this.findById(id);
        utenteDAO.delete(found);
    }

    public Utente findByIdAndUpdate(UUID id, Utente body) {
        Utente found = this.findById(id);
        found.setSurname(body.getSurname());
        found.setName(body.getName());
        found.setEmail(body.getEmail());
        found.setPassword(body.getPassword());
        return utenteDAO.save(found);
    }


    public Utente findByEmail(String email) throws NotFoundException {
        return utenteDAO.findByEmail(email).orElseThrow(() -> new NotFoundException("Utente con email " + email + " non trovata!"));
    }

    public Page<Utente> getUtente(int page, int size, String orderBy) {
        return null;
    }
}
