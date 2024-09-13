package fragnito.U5W2D5.entities;

import fragnito.U5W2D5.enums.StatoViaggio;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "viaggi")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Viaggio {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String destinazione;
    private LocalDate data;
    @Enumerated(EnumType.STRING)
    private StatoViaggio stato;
}
