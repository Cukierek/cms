package pl.bottega.cms.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.bottega.cms.application.MovieDto;
import pl.bottega.cms.application.MovieFinder;
import pl.bottega.cms.model.Cinema;
import pl.bottega.cms.model.CinemaRepository;
import pl.bottega.cms.model.Movie;
import pl.bottega.cms.model.commands.CommandInvalidException;
import pl.bottega.cms.model.commands.ValidationErrors;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JpaMovieFinder implements MovieFinder {

	private EntityManager entityManager;
	private ValidationErrors validationErrors;
	private CinemaRepository cinemaRepository;

	@Autowired
	public JpaMovieFinder(EntityManager entityManager, ValidationErrors valdationErrors, CinemaRepository cinemaRepository) {
		this.entityManager = entityManager;
		this.validationErrors = valdationErrors;
		this.cinemaRepository = cinemaRepository;
	}

	@Override
	public List<MovieDto> getMovies(Long cinemaId, LocalDate date) {
		validateParameters(cinemaId, date);
		Cinema cinema = entityManager.find(Cinema.class, cinemaId);
		Query query = entityManager.createQuery(
				"SELECT DISTINCT ( m ) " +
						"FROM Movie m " +
						"JOIN FETCH m.shows s " +
						"JOIN m.actors " +
						"JOIN m.genres " +
						"WHERE s.cinema = :cinema AND s.date BETWEEN :fromTime AND :toTime " +
						"GROUP BY s.id " +
						"ORDER BY m.title ASC");
		query.setParameter("cinema", cinema);
		query.setParameter("fromTime", date.atTime(LocalTime.MIDNIGHT));
		query.setParameter("toTime", date.atTime(LocalTime.MAX));
		List<Movie> movies = query.getResultList();
		return movies.stream().map(MovieDto::new).collect(Collectors.toList());
	}

	private void validateParameters(Long cinemaId, LocalDate date) {
		validateCinema(cinemaId);
		validateDate(date);
		if (validationErrors.any()) throw new CommandInvalidException(validationErrors);
	}

	private void validateCinema(Long cinemaId) {
		if (cinemaRepository.get(cinemaId) == null) validationErrors.add("cinemaId", "No cinema of given id");
	}

	private void validateDate(LocalDate date) {
		if(date == null) validationErrors.add("date", "Can't be empty");
	}
}