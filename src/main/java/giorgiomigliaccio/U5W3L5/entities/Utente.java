package giorgiomigliaccio.U5W3L5.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.HashSet;
import java.util.Set;
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

        @ManyToMany
        @JoinTable(name = "prenotazioni",
                joinColumns = @JoinColumn(name = "utente_id"),
                inverseJoinColumns = @JoinColumn(name = "evento_id"))
        private Set<Evento> eventiPartecipati = new HashSet<>();

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getSurname() {
                return surname;
        }

        public void setSurname(String surname) {
                this.surname = surname;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public Role getRole() {
                return role;
        }

        public void setRole(Role role) {
                this.role = role;
        }

        public Utente(String name, String surname, String email, String password, Role role) {
                this.name = name;
                this.surname = surname;
                this.email = email;
                this.password = password;
                this.role = role;
        }

        @Override
        public String toString() {
                return "Utente{" +
                        "id=" + id +
                        ", name='" + name + '\'' +
                        ", surname='" + surname + '\'' +
                        ", email='" + email + '\'' +
                        ", password='" + password + '\'' +
                        ", role=" + role +
                        ", eventiPartecipati=" + eventiPartecipati +
                        '}';
        }
}
