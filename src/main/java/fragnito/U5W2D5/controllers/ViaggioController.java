package fragnito.U5W2D5.controllers;

import fragnito.U5W2D5.entities.Viaggio;
import fragnito.U5W2D5.exceptions.Validation;
import fragnito.U5W2D5.payloads.NewViaggioDTO;
import fragnito.U5W2D5.payloads.RespDTO;
import fragnito.U5W2D5.services.ViaggioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/viaggi")
public class ViaggioController {
    @Autowired
    private ViaggioService viaggioService;

    @Autowired
    private Validation validation;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RespDTO postViaggio(@RequestBody @Validated NewViaggioDTO body, BindingResult validation) {
        this.validation.validate(validation);
        Viaggio newViaggio = this.viaggioService.saveViaggio(body);
        return new RespDTO(newViaggio.getId());
    }

    @GetMapping
    public List<Viaggio> getAllViaggi() {
        return this.viaggioService.getAllViaggi();
    }

    @GetMapping("/{viaggioId}")
    public Viaggio getViaggioById(@PathVariable int viaggioId) {
        return this.viaggioService.getViaggioById(viaggioId);
    }

    @PutMapping("/{viaggioId}")
    public RespDTO putViaggio(@PathVariable int viaggioId, @RequestBody @Validated NewViaggioDTO body, BindingResult validation) {
        this.validation.validate(validation);
        Viaggio updatedViaggio = this.viaggioService.putViaggio(viaggioId, body);
        return new RespDTO(updatedViaggio.getId());
    }

    @DeleteMapping("/{viaggioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteViaggio(@PathVariable int viaggioId) {
        this.viaggioService.deleteViaggio(viaggioId);
    }
}
