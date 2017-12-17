package pl.bottega.cms.ui;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.bottega.cms.api.CinemaDto;
import pl.bottega.cms.api.CinemaFinder;
import pl.bottega.cms.domain.commands.CreateCinemaCommand;

import java.util.List;

@RestController
public class CinemaController {

    private CinemaFinder cinemaFinder;

    void create(CreateCinemaCommand cmd){}

    @GetMapping("/cinemas/")
    public List<CinemaDto> getAllCinemas(@PathVariable String city) {
        return cinemaFinder.getAll();
    }

}
