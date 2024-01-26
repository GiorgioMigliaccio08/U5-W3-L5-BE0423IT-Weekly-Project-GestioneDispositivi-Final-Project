package giorgiomigliaccio.U5W3L5.services;


import giorgiomigliaccio.U5W3L5.entities.Utente;
import giorgiomigliaccio.U5W3L5.payloads.NewUtenteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import giorgiomigliaccio.U5W3L5.exceptions.UnauthorizedException;
import giorgiomigliaccio.U5W3L5.security.JWTTools;
import giorgiomigliaccio.U5W3L5.repositories.UtenteDAO;
import giorgiomigliaccio.U5W3L5.payloads.UtenteLoginDTO;
import giorgiomigliaccio.U5W3L5.exceptions.BadRequestException;
import giorgiomigliaccio.U5W3L5.entities.Role;

@Service
public class AuthService {
    @Autowired
    private UtenteService usersService;

    @Autowired
    private UtenteDAO usersDAO;

    @Autowired
    private PasswordEncoder bcrypt;

    @Autowired
    private JWTTools jwtTools;


    public String authenticateUser(UtenteLoginDTO body) {
        Utente user = usersService.findByEmail(body.email());
        if (bcrypt.matches(body.password(), user.getPassword())) {
            return jwtTools.createToken(user);
        } else {
            throw new UnauthorizedException("Credenziali non valide!");
        }
    }

    public Utente save(NewUtenteDTO body) {
        usersDAO.findByEmail(body.email()).ifPresent(user -> {
            throw new BadRequestException("L'email " + user.getEmail() + " è già in uso!");
        });

        Utente newUser = new Utente();
        newUser.setSurname(body.surname());
        newUser.setName(body.name());
        newUser.setEmail(body.email());
        // newUser.setPassword(body.password());
        newUser.setPassword(bcrypt.encode(body.password()));
        newUser.setRole(Role. UTENTE_NORMALE);
        return usersDAO.save(newUser);
    }
}
