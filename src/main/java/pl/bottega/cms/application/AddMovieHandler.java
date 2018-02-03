package pl.bottega.cms.application;

import org.springframework.stereotype.Component;
import pl.bottega.cms.model.Movie;
import pl.bottega.cms.model.MovieRepository;
import pl.bottega.cms.model.commands.Command;
import pl.bottega.cms.model.commands.AddMovieCommand;

@Component
public class AddMovieHandler implements Handler<AddMovieCommand>{

    private MovieRepository movieRepository;

    public AddMovieHandler(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void handle(AddMovieCommand cmd) {
     Movie movie = new Movie(
             cmd.getTitle(),
             cmd.getDescription(),
             cmd.getActors(),
             cmd.getGenres(),
             cmd.getMinAge(),
             cmd.getLength());

     movieRepository.save(movie);
    }

    @Override
    public Class<? extends Command> getSupportedCommandClass() {
        return null;
    }
}
