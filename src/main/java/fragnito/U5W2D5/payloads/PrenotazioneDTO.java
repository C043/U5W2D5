package fragnito.U5W2D5.payloads;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record PrenotazioneDTO(
        @NotNull(message = "L'id del dipendente è obbligatorio")
        @Min(value = 1, message = "L'id del dipendente è obbligatorio")
        int dipendenteId,
        @NotNull(message = "L'id del viaggio è obbligatorio")
        @Min(value = 1, message = "L'id del viaggio è obbligatorio")
        int viaggioId,
        String note
) {
}
