package fragnito.U5W2D5.services;

import fragnito.U5W2D5.entities.Dipendente;
import fragnito.U5W2D5.exceptions.ValidationException;
import fragnito.U5W2D5.payloads.NewDipendenteDTO;
import fragnito.U5W2D5.repositories.DipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DipendenteService {
    @Autowired
    private DipendenteRepository dipendenteRepository;

    public Dipendente saveDipendente(NewDipendenteDTO body) {
        if (this.dipendenteRepository.existsByEmail(body.email())) throw new ValidationException("Esiste già un utente con questa email.");
        Dipendente newDipendente = new Dipendente(body.username(), body.nome(), body.cognome(), body.email());
        newDipendente.setAvatar("https://ui-avatars.com/api/?name=" + newDipendente.getNome() + "+" + newDipendente.getCognome());
        this.dipendenteRepository.save(newDipendente);
        return newDipendente;
    }
}
