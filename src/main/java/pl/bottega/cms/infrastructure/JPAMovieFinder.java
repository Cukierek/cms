package pl.bottega.cms.infrastructure;

import pl.bottega.cms.application.MovieDto;
import pl.bottega.cms.application.MovieFinder;

import java.time.LocalDate;
import java.util.List;

public class JPAMovieFinder implements MovieFinder{

    @Override
    public List<MovieDto> getMovies(Long cinemaId, LocalDate date) {

        return null;
    }
}
