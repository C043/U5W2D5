package fragnito.U5W2D5.services;

import fragnito.U5W2D5.entities.Dipendente;
import fragnito.U5W2D5.entities.Prenotazione;
import fragnito.U5W2D5.entities.Viaggio;
import fragnito.U5W2D5.exceptions.BadRequestException;
import fragnito.U5W2D5.exceptions.NotFoundException;
import fragnito.U5W2D5.payloads.PrenotazioneDTO;
import fragnito.U5W2D5.repositories.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrenotazioneService {
    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private DipendenteService dipendenteService;

    @Autowired
    private ViaggioService viaggioService;

    public Prenotazione savePrenotazione(PrenotazioneDTO body) {
        Viaggio viaggio = this.viaggioService.getViaggioById(body.viaggioId());
        Dipendente dipendente = this.dipendenteService.getDipendenteById(body.dipendenteId());
        if (!this.prenotazioneRepository.filterPrenotazioniByUserAndData(dipendente.getId(), viaggio.getData()).isEmpty())
            throw new BadRequestException("Il dipendente " +
                    "è già impegnato nella data richiesta");
        Prenotazione prenotazione = new Prenotazione(dipendente, viaggio, body.note());
        this.prenotazioneRepository.save(prenotazione);
        return prenotazione;
    }

    public List<Prenotazione> getAllPrenotazioni() {
        return this.prenotazioneRepository.findAll();
    }

    public Prenotazione getPrenotazioneById(int id) {
        return this.prenotazioneRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }
}
