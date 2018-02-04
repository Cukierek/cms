package pl.bottega.cms.application;

import org.springframework.stereotype.Component;
import pl.bottega.cms.model.Movie;
import pl.bottega.cms.model.MovieRepository;
import pl.bottega.cms.model.commands.Command;
import pl.bottega.cms.model.commands.CommandInvalidException;
import pl.bottega.cms.model.commands.CreateMovieCommand;
import pl.bottega.cms.model.commands.ValidationErrors;

import javax.transaction.Transactional;

@Component
public class CreateMovieHandler implements Handler<CreateMovieCommand, Void> {

	private MovieRepository movieRepository;
	private ValidationErrors validationErrors;

	public CreateMovieHandler(MovieRepository movieRepository, ValidationErrors validationErrors) {
		this.movieRepository = movieRepository;
		this.validationErrors = validationErrors;
	}

	@Override
	@Transactional
	public Void handle(CreateMovieCommand cmd) {
		validateMovieParameters(cmd);
		Movie movie = new Movie(cmd);
		movieRepository.save(movie);
		return null;
	}

	public Void validateMovieParameters(CreateMovieCommand cmd) {
		boolean commandInvalid = false;
		if (cmd.getTitle().isEmpty()) {
			validationErrors.add("Title", "Can't be empty");
			commandInvalid = true;
		}
		if (cmd.getDescription().isEmpty()) {
			validationErrors.add("Description", "Can't be empty");
			commandInvalid = true;
		}
		if (cmd.getActors().isEmpty()) {
			validationErrors.add("Actors", "Can't be empty");
			commandInvalid = true;
		}
		if (cmd.getGenres().isEmpty()) {
			validationErrors.add("Genres", "Can't be empty");
			commandInvalid = true;
		}
		if (cmd.getMinAge() == null) {
			validationErrors.add("Minimal age", "Can't be empty");
			commandInvalid = true;
		}
		if (cmd.getMinAge() < 0) {
			validationErrors.add("Minimal age", "Can't be less than 0");
			commandInvalid = true;
		}
		if (cmd.getLength() == null) {
			validationErrors.add("Length", "Can't be empty");
			commandInvalid = true;
		}
		if (cmd.getLength() < 0) {
			validationErrors.add("Length", "Can't be less than 0");
			commandInvalid = true;
		}
		if (commandInvalid) throw new CommandInvalidException(validationErrors);
		return null;
	}

	@Override
	public Class<? extends Command> getSupportedCommandClass() {
		return CreateMovieCommand.class;
	}
}
