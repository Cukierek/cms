package pl.bottega.cms.ui;

import org.springframework.web.bind.annotation.*;
import pl.bottega.cms.api.CinemaDto;
import pl.bottega.cms.api.CinemaFinder;
import pl.bottega.cms.api.CommandGateway;
import pl.bottega.cms.domain.commands.CreateCinemaCommand;
import pl.bottega.cms.domain.commands.CreateShowsCommand;

import java.util.List;

@RestController
public class CinemaController {

    private CinemaFinder cinemaFinder;
    private CommandGateway gateway;

    public CinemaController(CinemaFinder cinemaFinder, CommandGateway gateway) {
        this.cinemaFinder = cinemaFinder;
        this.gateway = gateway;
    }

    @PutMapping
    public void create(@RequestBody CreateCinemaCommand cmd){
        gateway.execute(cmd);
    }

    @GetMapping("/cinemas")
    public List<CinemaDto> getAllCinemas() {
        return cinemaFinder.getAll();
    }

    @PutMapping("/{cinemaId}/shows")
    public void createShows(@PathVariable Long cinemaId, @RequestBody CreateShowsCommand cmd) {
    	cmd.setCinemaId(cinemaId);
    	gateway.execute(cmd);
    }

}
