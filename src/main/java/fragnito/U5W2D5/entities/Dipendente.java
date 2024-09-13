package fragnito.U5W2D5.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "dipendenti")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Dipendente {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String nome;
    private String cognome;
    private String email;
    private String avatar;
}
