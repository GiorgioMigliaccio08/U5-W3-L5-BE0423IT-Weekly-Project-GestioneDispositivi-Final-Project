package giorgiomigliaccio.U5W3L5.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import giorgiomigliaccio.U5W3L5.entities.Utente;
import giorgiomigliaccio.U5W3L5.exceptions.BadRequestException;
import giorgiomigliaccio.U5W3L5.payloads.NewUtenteDTO;
import giorgiomigliaccio.U5W3L5.payloads.NewUtenteResponseDTO;
import giorgiomigliaccio.U5W3L5.payloads.UtenteLoginDTO;
import giorgiomigliaccio.U5W3L5.payloads.UtenteLoginResponseDTO;
import giorgiomigliaccio.U5W3L5.services.AuthService;
import giorgiomigliaccio.U5W3L5.services.UtenteService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService authService;
    @Autowired
    UtenteService usersService;

    @PostMapping("/login")
    public UtenteLoginResponseDTO login(@RequestBody UtenteLoginDTO body) {
        String accessToken = authService.authenticateUser(body);
        return new UtenteLoginResponseDTO(accessToken);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NewUtenteResponseDTO createUser(@RequestBody @Validated NewUtenteDTO newUserPayload, BindingResult validation) {
        System.out.println(validation);
        if (validation.hasErrors()) {
            System.out.println(validation.getAllErrors());
            throw new BadRequestException("Ci sono errori nel payload!");
        } else {
            Utente newUser = usersService.save(newUserPayload);

            return new NewUtenteResponseDTO(newUser.getId());
        }
    }
}
