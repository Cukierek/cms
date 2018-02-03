package pl.bottega.cms.application;

import org.springframework.stereotype.Component;
import pl.bottega.cms.model.Movie;
import pl.bottega.cms.model.MovieRepository;
import pl.bottega.cms.model.commands.Command;
import pl.bottega.cms.model.commands.CreateMovieCommand;

@Component
public class CreateMovieHandler implements Handler<CreateMovieCommand> {

	private MovieRepository movieRepository;

	public CreateMovieHandler(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	@Override
	public void handle(CreateMovieCommand cmd) {
		Movie movie = new Movie(cmd);
		movieRepository.save(movie);
	}

	@Override
	public Class<? extends Command> getSupportedCommandClass() {
		return null;
	}
}
