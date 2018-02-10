package pl.bottega.cms.ui;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.bottega.cms.application.CommandGateway;
import pl.bottega.cms.model.commands.CalculatePriceCommand;
import pl.bottega.cms.model.commands.CreateReservationCommand;

@RestController
public class ReservationController {

    private CommandGateway commandGateway;

    public ReservationController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PutMapping("/reservations")
    public void create(@RequestBody CreateReservationCommand cmd) {
        commandGateway.execute(cmd);
    }

    @PostMapping("/price_caluclator")
    public void calculatePrice(@RequestBody CalculatePriceCommand cmd) {
    	commandGateway.execute(cmd);
    }
}
