package fragnito.U5W2D5.services;

import fragnito.U5W2D5.entities.Dipendente;
import fragnito.U5W2D5.entities.Prenotazione;
import fragnito.U5W2D5.entities.Viaggio;
import fragnito.U5W2D5.exceptions.BadRequestException;
import fragnito.U5W2D5.exceptions.NotFoundException;
import fragnito.U5W2D5.payloads.PrenotazioneDTO;
import fragnito.U5W2D5.payloads.PrenotazioneOwnershipDTO;
import fragnito.U5W2D5.repositories.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public Prenotazione updatePrenotazione(int id, PrenotazioneDTO body) {
        Prenotazione found = this.getPrenotazioneById(id);
        if (found.getDipendente().getId() != body.dipendenteId()) throw new BadRequestException("Endpoint sbagliato per cambiare assegnazione di prenotazione");
        Viaggio viaggio = this.viaggioService.getViaggioById(body.viaggioId());
        found.setNote(body.note());
        found.setViaggio(viaggio);
        found.setDataRichiesta(LocalDate.now());
        this.prenotazioneRepository.save(found);
        return found;
    }

    public void deletePrenotazione(int id) {
        Prenotazione found = this.getPrenotazioneById(id);
        this.prenotazioneRepository.delete(found);
    }

    public Prenotazione changePrenotazioneOwnership(int prenotazioneId, PrenotazioneOwnershipDTO body) {
        Prenotazione found = this.getPrenotazioneById(prenotazioneId);
        Viaggio viaggio = this.viaggioService.getViaggioById(found.getViaggio().getId());
        Dipendente dipendente = this.dipendenteService.getDipendenteById(body.dipendenteId());
        if (!this.prenotazioneRepository.filterPrenotazioniByUserAndData(dipendente.getId(), viaggio.getData()).isEmpty())
            throw new BadRequestException("Il dipendente " +
                    "è già impegnato nella data richiesta");
        found.setDipendente(dipendente);
        found.setDataRichiesta(LocalDate.now());
        this.prenotazioneRepository.save(found);
        return found;
    }
}
