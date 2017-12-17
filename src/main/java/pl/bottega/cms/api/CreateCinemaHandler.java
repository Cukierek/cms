package pl.bottega.cms.api;

import org.springframework.stereotype.Component;
import pl.bottega.cms.domain.Cinema;
import pl.bottega.cms.domain.CinemaRepository;
import pl.bottega.cms.domain.commands.Command;
import pl.bottega.cms.domain.commands.CreateCinemaCommand;

import javax.transaction.Transactional;

@Component
public class CreateCinemaHandler implements Handler<CreateCinemaCommand> {

    private CinemaRepository cinemaRepository;

    public CreateCinemaHandler(CinemaRepository cinemaRepository) {
        this.cinemaRepository = cinemaRepository;
    }

    @Transactional
    public void handle(CreateCinemaCommand cmd){
        Cinema cinema = new Cinema(cmd);
        cinemaRepository.save(cinema);
    }

    @Override
    public Class<? extends Command> getSupportedCommandClass() {
        return CreateCinemaCommand.class;
    }
}
