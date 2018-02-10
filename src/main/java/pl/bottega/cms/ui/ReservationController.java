package pl.bottega.cms.ui;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.bottega.cms.application.CommandGateway;
import pl.bottega.cms.model.commands.CalculatePricesCommand;
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

    @PostMapping("/price_calculator")
    public void calculatePrice(@RequestBody CalculatePricesCommand cmd) {
    	commandGateway.execute(cmd);
    }
}
