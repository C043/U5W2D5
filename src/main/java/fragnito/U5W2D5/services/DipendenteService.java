package fragnito.U5W2D5.services;

import fragnito.U5W2D5.entities.Dipendente;
import fragnito.U5W2D5.exceptions.BadRequestException;
import fragnito.U5W2D5.exceptions.NotFoundException;
import fragnito.U5W2D5.payloads.NewDipendenteDTO;
import fragnito.U5W2D5.repositories.DipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DipendenteService {
    @Autowired
    private DipendenteRepository dipendenteRepository;

    public Dipendente saveDipendente(NewDipendenteDTO body) {
        if (this.dipendenteRepository.existsByEmail(body.email())) throw new BadRequestException("Esiste già un utente con questa email.");
        Dipendente newDipendente = new Dipendente(body.username(), body.nome(), body.cognome(), body.email());
        newDipendente.setAvatar("https://ui-avatars.com/api/?name=" + newDipendente.getNome() + "+" + newDipendente.getCognome());
        this.dipendenteRepository.save(newDipendente);
        return newDipendente;
    }

    public List<Dipendente> getAllDipendenti() {
        return this.dipendenteRepository.findAll();
    }

    public Dipendente getDipendenteById(int id) {
        return this.dipendenteRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Dipendente updateDipendente(int id, NewDipendenteDTO updatedDipendente) {
        Dipendente found = this.getDipendenteById(id);
        found.setNome(updatedDipendente.nome());
        found.setCognome(updatedDipendente.cognome());
        found.setEmail(updatedDipendente.email());
        found.setUsername(updatedDipendente.username());
        this.dipendenteRepository.save(found);
        return found;
    }
}
