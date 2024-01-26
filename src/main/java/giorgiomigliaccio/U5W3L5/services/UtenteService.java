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
    private UtenteDAO usersDAO;

    public Page<Utente> getUsers(int page, int size, String orderBy) {
        if (size >= 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return usersDAO.findAll(pageable);
    }


    public Utente findById(UUID id) {
        return usersDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(UUID id) {
        Utente found = this.findById(id);
        usersDAO.delete(found);
    }

    public Utente findByIdAndUpdate(UUID id, Utente body) {
        Utente found = this.findById(id);
        found.setSurname(body.getSurname());
        found.setName(body.getName());
        found.setEmail(body.getEmail());
        found.setPassword(body.getPassword());
        return usersDAO.save(found);
    }


    public Utente findByEmail(String email) throws NotFoundException {
        return usersDAO.findByEmail(email).orElseThrow(() -> new NotFoundException("Utente con email " + email + " non trovata!"));
    }

}
