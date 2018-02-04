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
		Movie movie = new Movie(cmd);
		movieRepository.save(movie);
		return null;
	}

	@Override
	public Class<? extends Command> getSupportedCommandClass() {
		return CreateMovieCommand.class;
	}
}
