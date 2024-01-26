package giorgiomigliaccio.U5W3L5.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
        return usersService.getUsers(page, size, orderBy);
    }

    // /me endpoints
    @GetMapping("/me")
    public Utente getProfile(@AuthenticationPrincipal Utente currentUser) {
        return currentUser;
    }


    @PutMapping("/me")
    public Utente getMeAndUpdate(@AuthenticationPrincipal Utente currentUser, @RequestBody Utente body) {
        return usersService.findByIdAndUpdate(currentUser.getId(), body);
    }

    @DeleteMapping("/me")
    public void getMeAnDelete(@AuthenticationPrincipal Utente currentUser) {
        usersService.findByIdAndDelete(currentUser.getId());
    }


    @GetMapping("/{userId}")
    public Utente getUserById(@PathVariable UUID userId) {
        return usersService.findById(userId);
    }


    @PutMapping("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Utente getUserByIdAndUpdate(@PathVariable UUID userId, @RequestBody Utente modifiedUserPayload) {
        return usersService.findByIdAndUpdate(userId, modifiedUserPayload);
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getUserByIdAndDelete(@PathVariable UUID userId) {
        usersService.findByIdAndDelete(userId);
    }
}
