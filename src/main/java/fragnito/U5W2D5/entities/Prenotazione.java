package fragnito.U5W2D5.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "prenotazioni")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Prenotazione {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "dipendente_id")
    private Dipendente dipendente;
    @ManyToOne
    @JoinColumn(name = "viaggio_id")
    private Viaggio viaggio;
    @Column(name = "data_richiesta")
    private LocalDate dataRichiesta;
    private String note;
}
