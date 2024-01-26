package giorgiomigliaccio.U5W3L5.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.UUID;


@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
public class Utente {

        @Id
        @GeneratedValue
        private UUID id;
        private String name;
        private String surname;
        private String email;
        private String password;
        @Enumerated(EnumType.STRING)
        private Role role;


}
