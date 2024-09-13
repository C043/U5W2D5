package fragnito.U5W2D5.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NewDipendenteDTO(
        @NotNull
        @Size(min = 3, max = 10, message = "Username deve contenere minimo 3 caratteri e massimo 10")
        String username,
        @NotNull
        @Size(min = 3, max = 10, message = "Il nome deve contenere minimo 3 caratteri e massimo 10")
        String nome,
        @NotNull
        @Size(min = 3, max = 10, message = "Il cognome deve contenere minimo 3 caratteri e massimo 10")
        String cognome,
        @NotNull
        @Email(message = "L'email inserita non è un indirizzo valido")
        String email
) {
}
