package fragnito.U5W2D5.services;

import fragnito.U5W2D5.entities.Viaggio;
import fragnito.U5W2D5.enums.StatoViaggio;
import fragnito.U5W2D5.exceptions.NotFoundException;
import fragnito.U5W2D5.payloads.NewViaggioDTO;
import fragnito.U5W2D5.repositories.ViaggioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViaggioService {
    @Autowired
    private ViaggioRepository viaggioRepository;

    public Viaggio saveViaggio(NewViaggioDTO body) {
        Viaggio newViaggio = new Viaggio(body.destinazione(), body.data(), StatoViaggio.valueOf(body.stato()));
        this.viaggioRepository.save(newViaggio);
        return newViaggio;
    }

    public List<Viaggio> getAllViaggi() {
        return this.viaggioRepository.findAll();
    }

    public Viaggio getViaggioById(int id) {
        return this.viaggioRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Viaggio putViaggio(int id, NewViaggioDTO body) {
        Viaggio found = this.getViaggioById(id);
        found.setDestinazione(body.destinazione());
        found.setStato(StatoViaggio.valueOf(body.stato()));
        found.setData(body.data());
        this.viaggioRepository.save(found);
        return found;
    }
}
