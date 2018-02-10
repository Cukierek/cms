package pl.bottega.cms.application;

import java.time.LocalDate;
import java.util.List;

public interface MovieFinder {
	List<MovieDto> getMovies(Long cinemaId, LocalDate date);
}
