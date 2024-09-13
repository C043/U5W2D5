package fragnito.U5W2D5.controllers;

import fragnito.U5W2D5.entities.Dipendente;
import fragnito.U5W2D5.exceptions.BadRequestException;
import fragnito.U5W2D5.payloads.NewDipendenteDTO;
import fragnito.U5W2D5.payloads.RespDipendenteDTO;
import fragnito.U5W2D5.services.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/dipendenti")
public class DipendenteController {
    @Autowired
    private DipendenteService dipendenteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RespDipendenteDTO postDipendente(@RequestBody @Validated NewDipendenteDTO body, BindingResult validation) {
        String messages = validation.getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining(". "));
        if (validation.hasErrors()) throw new BadRequestException("Ci sono stati degli errori: " + messages);
        Dipendente dipendente = this.dipendenteService.saveDipendente(body);
        return new RespDipendenteDTO(dipendente.getId());
    }

    @GetMapping
    public List<Dipendente> getAllDipendenti() {
        return this.dipendenteService.getAllDipendenti();
    }

    @GetMapping("/{dipendenteId}")
    public Dipendente getDipendenteById(@PathVariable int dipendenteId) {
        return this.dipendenteService.getDipendenteById(dipendenteId);
    }

    @PutMapping("/{dipendenteId}")
    public RespDipendenteDTO putDipendente(@PathVariable int dipendenteId, @RequestBody @Validated NewDipendenteDTO body, BindingResult validation) {
        String messages = validation.getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining(". "));
        if (validation.hasErrors()) throw new BadRequestException("Ci sono stati degli errori: " + messages);
        Dipendente updatedDipendente = this.dipendenteService.updateDipendente(dipendenteId, body);
        return new RespDipendenteDTO(updatedDipendente.getId());
    }

    @DeleteMapping("/{dipendenteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDipendente(@PathVariable int dipendenteId) {
        this.dipendenteService.deleteDipendente(dipendenteId);
    }
}
