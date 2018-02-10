package pl.bottega.cms.ui;

import org.springframework.web.bind.annotation.*;
import pl.bottega.cms.application.CommandGateway;
import pl.bottega.cms.model.TicketPrices;
import pl.bottega.cms.model.commands.CreateMovieCommand;
import pl.bottega.cms.model.commands.SetTicketPricesCommand;

import java.math.BigDecimal;
import java.util.Map;


@RestController
@RequestMapping("/movies")
public class MovieController {

	private CommandGateway commandGateway;

	public MovieController(CommandGateway commandGateway) {
		this.commandGateway = commandGateway;
	}

	@PutMapping
	public void create(@RequestBody CreateMovieCommand cmd) {
		commandGateway.execute(cmd);
	}

	@PutMapping("/{movieId}/prices")
	void setTicketPrices(@PathVariable Integer movieId, @RequestBody Map<String, BigDecimal> prices){
		SetTicketPricesCommand setTicketPricesCommand = new SetTicketPricesCommand();
		setTicketPricesCommand.setMovieId(movieId);
		setTicketPricesCommand.setPrices(prices);
		commandGateway.execute(setTicketPricesCommand);
	}


}
