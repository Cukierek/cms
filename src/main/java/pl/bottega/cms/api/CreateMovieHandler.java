package pl.bottega.cms.api;

import org.springframework.stereotype.Component;
import pl.bottega.cms.domain.Movie;
import pl.bottega.cms.domain.MovieRepository;
import pl.bottega.cms.domain.commands.Command;
import pl.bottega.cms.domain.commands.CreateMovieCommand;

import javax.transaction.Transactional;

@Component
public class CreateMovieHandler implements Handler<CreateMovieCommand> {

    private MovieRepository movieRepository;

    public CreateMovieHandler(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    @Transactional
    public void handle(CreateMovieCommand command) {
        Movie movie = new Movie(command);
        movieRepository.save(movie);
    }

    @Override
    public Class<? extends Command> getSupportedCommandClass() {
        return CreateMovieCommand.class;
    }

}
