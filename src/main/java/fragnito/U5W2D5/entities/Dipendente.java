package fragnito.U5W2D5.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "dipendenti")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Dipendente {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue
    private int id;
    private String username;
    private String nome;
    private String cognome;
    private String email;
    private String avatar;
}
