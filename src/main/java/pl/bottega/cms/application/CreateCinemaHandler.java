package pl.bottega.cms.application;

import org.springframework.stereotype.Component;
import pl.bottega.cms.model.Cinema;
import pl.bottega.cms.model.CinemaRepository;
import pl.bottega.cms.model.commands.Command;
import pl.bottega.cms.model.commands.CommandInvalidException;
import pl.bottega.cms.model.commands.CreateCinemaCommand;
import pl.bottega.cms.model.commands.ValidationErrors;

import javax.transaction.Transactional;
import java.util.Optional;

@Component
public class CreateCinemaHandler implements Handler<CreateCinemaCommand, Void> {

	private CinemaRepository cinemaRepository;
	private ValidationErrors validationErrors;

	public CreateCinemaHandler(CinemaRepository cinemaRepository, ValidationErrors validationErrors) {
		this.cinemaRepository = cinemaRepository;
		this.validationErrors = validationErrors;
	}

	@Transactional
	public Void handle(CreateCinemaCommand cmd) {
		validateCinemaPresence(cmd);
		validateCinemaParameters(cmd);
		Cinema cinema = new Cinema(cmd);
		cinemaRepository.save(cinema);
		return null;
	}

	@Override
	public Class<? extends Command> getSupportedCommandClass() {
		return CreateCinemaCommand.class;
	}

	public void validateCinemaPresence(CreateCinemaCommand cmd) {
		Optional<Cinema> cinema = cinemaRepository.findByNameAndCity(cmd.getName(), cmd.getCity());
		if (isPresentInRepository(cinema)) {
			validationErrors.add("cinema", "Cinema with the given name and city has already been created");
			throw new CommandInvalidException(validationErrors);
		}
	}

	public void validateCinemaParameters(CreateCinemaCommand cmd) {
		boolean commandInvalid = false;
		if (cmd.getName().isEmpty()) {
			validationErrors.add("Name", "Can't be empty");
			commandInvalid = true;
		}
		if (cmd.getCity().isEmpty()) {
			validationErrors.add("City", "Can't be empty");
			commandInvalid = true;
		}
		if (commandInvalid) throw new CommandInvalidException(validationErrors);
	}

	private boolean isPresentInRepository(Optional<Cinema> cinema) {
		return cinema.isPresent();
	}
}
