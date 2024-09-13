package fragnito.U5W2D5.services;

import fragnito.U5W2D5.entities.Viaggio;
import fragnito.U5W2D5.payloads.NewViaggioDTO;
import fragnito.U5W2D5.repositories.ViaggioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViaggioService {
    @Autowired
    private ViaggioRepository viaggioRepository;

    public Viaggio saveViaggio(NewViaggioDTO body) {
        Viaggio newViaggio = new Viaggio(body.destinazione(), body.data(), body.stato());
        this.viaggioRepository.save(newViaggio);
        return newViaggio;
    }
}
