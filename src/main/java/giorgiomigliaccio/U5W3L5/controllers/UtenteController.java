package giorgiomigliaccio.U5W3L5.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import giorgiomigliaccio.U5W3L5.entities.Utente;
import giorgiomigliaccio.U5W3L5.services.UtenteService;

import java.util.UUID;

@RestController
@RequestMapping("/utente")
public class UtenteController {
    @Autowired
    private UtenteService usersService;

    @GetMapping
    public Page<Utente> getUsers(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size,
                               @RequestParam(defaultValue = "id") String orderBy) {
        return usersService.getUtente(page, size, orderBy);
    }

    @GetMapping("/{userId}")
    public Utente getUserById(@PathVariable UUID userId) {
        return usersService.findById(userId);
    }


    @PutMapping("/{userId}")
    public Utente getUserByIdAndUpdate(@PathVariable UUID utenteId, @RequestBody Utente modifiedUserPayload) {
        return usersService.findByIdAndUpdate(utenteId, modifiedUserPayload);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getUserByIdAndDelete(@PathVariable UUID userId) {
        usersService.findByIdAndDelete(userId);
    }
}
