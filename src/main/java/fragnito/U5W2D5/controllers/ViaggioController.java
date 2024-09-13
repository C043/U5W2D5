package fragnito.U5W2D5.controllers;

import fragnito.U5W2D5.entities.Viaggio;
import fragnito.U5W2D5.exceptions.Validation;
import fragnito.U5W2D5.payloads.NewViaggioDTO;
import fragnito.U5W2D5.payloads.RespDTO;
import fragnito.U5W2D5.services.ViaggioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/viaggi")
public class ViaggioController {
    @Autowired
    private ViaggioService viaggioService;

    @Autowired
    private Validation validation;

    public RespDTO postViaggio(@RequestBody @Validated NewViaggioDTO body, BindingResult validation) {
        this.validation.validate(validation);
        Viaggio newViaggio = this.viaggioService.saveViaggio(body);
        return new RespDTO(newViaggio.getId());
    }
}
