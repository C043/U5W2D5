package fragnito.U5W2D5.entities;

import fragnito.U5W2D5.enums.StatoViaggio;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "viaggi")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Viaggio {
    @Id
    @GeneratedValue
    private int id;
    private String destinazione;
    private LocalDate data;
    private StatoViaggio stato;
}
