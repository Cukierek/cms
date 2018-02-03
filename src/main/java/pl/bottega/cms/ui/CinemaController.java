package pl.bottega.cms.ui;

import org.springframework.web.bind.annotation.*;
import pl.bottega.cms.application.CinemaDto;
import pl.bottega.cms.application.CinemaFinder;
import pl.bottega.cms.application.CommandGateway;
import pl.bottega.cms.model.commands.CreateCinemaCommand;
import pl.bottega.cms.model.commands.CreateShowsCommand;

import java.util.List;

@RestController
@RequestMapping("/cinemas")
public class CinemaController {

	private CinemaFinder cinemaFinder;
	private CommandGateway gateway;

	public CinemaController(CinemaFinder cinemaFinder, CommandGateway gateway) {
		this.cinemaFinder = cinemaFinder;
		this.gateway = gateway;
	}

	@PutMapping
	public void create(@RequestBody CreateCinemaCommand cmd) {
		gateway.execute(cmd);
	}

	@GetMapping
	public List<CinemaDto> getAllCinemas() {
		return cinemaFinder.getAll();
	}

	@PutMapping("/cinemas/{cinemaId}/shows")
	public void createShows(@PathVariable Long cinemaId, @RequestBody CreateShowsCommand cmd) {
		cmd.setCinemaId(cinemaId);
		gateway.execute(cmd);
	}

}
