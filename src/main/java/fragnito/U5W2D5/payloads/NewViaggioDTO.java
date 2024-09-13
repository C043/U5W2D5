package fragnito.U5W2D5.payloads;

import fragnito.U5W2D5.enums.StatoViaggio;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record NewViaggioDTO(
        @NotNull(message = "La destinazione è obbligatoria")
        String destinazione,
        @NotNull(message = "La data è obbligatoria")
        LocalDate data,
        @NotNull(message = "Lo stato è obbligatorio")
        StatoViaggio stato
) {
}
