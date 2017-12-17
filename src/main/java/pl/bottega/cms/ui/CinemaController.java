package pl.bottega.cms.ui;

import org.springframework.web.bind.annotation.*;
import pl.bottega.cms.api.CinemaDto;
import pl.bottega.cms.api.CinemaFinder;
import pl.bottega.cms.api.CommandGateway;
import pl.bottega.cms.domain.commands.CreateCinemaCommand;

import java.util.List;

@RestController
public class CinemaController {

    private CinemaFinder cinemaFinder;
    private CommandGateway gateway;

    public CinemaController(CinemaFinder cinemaFinder, CommandGateway gateway) {
        this.cinemaFinder = cinemaFinder;
        this.gateway = gateway;
    }

    @PutMapping("/cinemas")
    public void create(@RequestBody CreateCinemaCommand cmd){
        gateway.execute(cmd);
    }

    @GetMapping("/cinemas")
    public List<CinemaDto> getAllCinemas(@PathVariable String city) {
        return cinemaFinder.getAll();
    }

}
