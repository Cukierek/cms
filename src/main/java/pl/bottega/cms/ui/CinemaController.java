package pl.bottega.cms.ui;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import pl.bottega.cms.application.*;
import pl.bottega.cms.model.commands.CreateCinemaCommand;
import pl.bottega.cms.model.commands.CreateShowsCommand;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/cinemas")
public class CinemaController {

    private CinemaFinder cinemaFinder;
    private CommandGateway gateway;
    private MovieFinder movieFinder;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd");


    public CinemaController(CinemaFinder cinemaFinder, CommandGateway gateway, MovieFinder movieFinder) {
        this.cinemaFinder = cinemaFinder;
        this.gateway = gateway;
        this.movieFinder = movieFinder;
    }

    @PutMapping
    public void create(@RequestBody CreateCinemaCommand cmd) {
        gateway.execute(cmd);
    }

    @GetMapping
    public List<CinemaDto> getAllCinemas() {
        return cinemaFinder.getAll();
    }

    @PutMapping("/{cinemaId}/shows")
    public void createShows(@PathVariable Long cinemaId, @RequestBody CreateShowsCommand cmd) {
        cmd.setCinemaId(cinemaId);
        gateway.execute(cmd);
    }

    @GetMapping("/{cinemaId}/movies")
    public List<MovieDto> getShows(
    		@PathVariable Long cinemaId,
		    @RequestParam @DateTimeFormat(pattern = "yyyy/MM/dd") LocalDate date) {
        return movieFinder.getMovies(cinemaId, date);
    }
}
