package fragnito.U5W2D5.payloads;

import jakarta.validation.constraints.Min;

public record PrenotazioneOwnershipDTO(
        @Min(value = 1, message = "L'id del dipendente è obbligatorio")
        int dipendenteId
) {
}
