package pl.bottega.cms.api;

import org.springframework.stereotype.Component;
import pl.bottega.cms.domain.Cinema;
import pl.bottega.cms.domain.CinemaRepository;
import pl.bottega.cms.domain.commands.Command;
import pl.bottega.cms.domain.commands.CommandInvalidException;
import pl.bottega.cms.domain.commands.CreateCinemaCommand;
import pl.bottega.cms.domain.commands.ValidationErrors;

import javax.transaction.Transactional;
import java.util.Optional;

@Component
public class CreateCinemaHandler implements Handler<CreateCinemaCommand> {

    private CinemaRepository cinemaRepository;
    private ValidationErrors validationErrors;

    public CreateCinemaHandler(CinemaRepository cinemaRepository, ValidationErrors validationErrors) {
        this.cinemaRepository = cinemaRepository;
        this.validationErrors = validationErrors;
    }

    @Transactional
    public void handle(CreateCinemaCommand cmd) {
        Cinema cinema = new Cinema(cmd);
        validateCinemaPresence(cmd);
        cinemaRepository.save(cinema);
    }

    @Override
    public Class<? extends Command> getSupportedCommandClass() {
        return CreateCinemaCommand.class;
    }

    public void validateCinemaPresence(CreateCinemaCommand cmd) {
        Optional<Cinema> cinema = cinemaRepository.findByNameAndCity(cmd.getName(), cmd.getCity());
        if (isCinemaExists(cinema)) {
            validationErrors.add("cinema", "Cinema with the given name and city has already been created");
            throw new CommandInvalidException(validationErrors);
        }
    }

    private boolean isCinemaExists(Optional<Cinema> cinema) {
        return cinema.isPresent();
    }
}
