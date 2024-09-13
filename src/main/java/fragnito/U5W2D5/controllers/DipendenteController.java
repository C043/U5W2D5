package fragnito.U5W2D5.controllers;

import fragnito.U5W2D5.entities.Dipendente;
import fragnito.U5W2D5.payloads.NewDipendenteDTO;
import fragnito.U5W2D5.payloads.RespDipendenteDTO;
import fragnito.U5W2D5.services.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dipendenti")
public class DipendenteController {
    @Autowired
    private DipendenteService dipendenteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RespDipendenteDTO postDipendente(@RequestBody NewDipendenteDTO body) {
        Dipendente dipendente = this.dipendenteService.saveDipendente(body);
        return new RespDipendenteDTO(dipendente.getId());
    }
}
