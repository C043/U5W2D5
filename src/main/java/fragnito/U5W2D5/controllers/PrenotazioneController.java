package fragnito.U5W2D5.controllers;

import fragnito.U5W2D5.entities.Prenotazione;
import fragnito.U5W2D5.exceptions.Validation;
import fragnito.U5W2D5.payloads.PrenotazioneDTO;
import fragnito.U5W2D5.payloads.RespDTO;
import fragnito.U5W2D5.services.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {
    @Autowired
    private PrenotazioneService prenotazioneService;
    @Autowired
    private Validation validation;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RespDTO postPrenotazione(@RequestBody @Validated PrenotazioneDTO body, BindingResult validation) {
        this.validation.validate(validation);
        Prenotazione prenotazione = this.prenotazioneService.savePrenotazione(body);
        return new RespDTO(prenotazione.getId());
    }

    @GetMapping
    public List<Prenotazione> getAllPrenotazioni() {
        return this.prenotazioneService.getAllPrenotazioni();
    }
}
