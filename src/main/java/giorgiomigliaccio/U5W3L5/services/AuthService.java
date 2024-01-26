package giorgiomigliaccio.U5W3L5.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import giorgiomigliaccio.U5W3L5.entities.Utente;
import giorgiomigliaccio.U5W3L5.exceptions.UnauthorizedException;
import giorgiomigliaccio.U5W3L5.payloads.UtenteLoginDTO;
import giorgiomigliaccio.U5W3L5.security.JWTTools;

@Service
public class AuthService {
    @Autowired
    private UtenteService usersService;

    @Autowired
    private JWTTools jwtTools;

    public String authenticateUser(UtenteLoginDTO body) {
        Utente user = usersService.findByEmail(body.email());

        if (body.password().equals(user.getPassword())) {

            return jwtTools.createToken(user);
        } else {

            throw new UnauthorizedException("Credenziali non valide!");
        }
    }
}
