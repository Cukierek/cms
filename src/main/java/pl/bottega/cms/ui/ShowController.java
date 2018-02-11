package pl.bottega.cms.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.bottega.cms.application.CinemaFinder;
import pl.bottega.cms.application.CinemaHallDto;

@RestController
public class ShowController {

    @Autowired
    private CinemaFinder cinemaFinder;

    @GetMapping("/shows/{showId}/seats")
    public CinemaHallDto getSeats(@PathVariable Long showId) {
        return cinemaFinder.getSeats(showId);
    }
}
