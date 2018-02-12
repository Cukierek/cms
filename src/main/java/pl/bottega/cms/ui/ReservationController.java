package pl.bottega.cms.ui;

import org.springframework.web.bind.annotation.*;
import pl.bottega.cms.application.CommandGateway;
import pl.bottega.cms.model.ReservationStatus;
import pl.bottega.cms.model.commands.CalculatePricesCommand;
import pl.bottega.cms.model.commands.CreateReservationCommand;

@RestController
public class ReservationController {

    private CommandGateway commandGateway;

    public ReservationController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PutMapping("/reservations")
    public ReservationNumber create(@RequestBody CreateReservationCommand cmd) {
        ReservationNumber reservationNumber = new ReservationNumber(commandGateway.execute(cmd));
        return reservationNumber;
    }

    @PostMapping("/price_calculator")
    public void calculatePrice(@RequestBody CalculatePricesCommand cmd) {
        commandGateway.execute(cmd);
    }

    @GetMapping("/reservations")
    public void search(@RequestParam("query") String query,
                       @RequestParam("status") ReservationStatus status){

//        ReservationQuery reservationQuery = new ReservationQuery(query, status);
//        commandGateway.execute(new GetReservationsCommand(reservationQuery));


    }


}
