package pl.bottega.cms.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.bottega.cms.application.MovieDto;
import pl.bottega.cms.application.MovieFinder;
import pl.bottega.cms.model.Cinema;
import pl.bottega.cms.model.Movie;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JPAMovieFinder implements MovieFinder {

    private EntityManager entityManager;

    @Autowired
    public JPAMovieFinder(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<MovieDto> getMovies(Long cinemaId, LocalDate date) {
        Cinema cinema = entityManager.find(Cinema.class, cinemaId);
        Query query = entityManager.createQuery(
                "SELECT DISTINCT ( m ) " +
                "FROM Movie m " +
                "JOIN FETCH m.shows s " +
                "JOIN FETCH m.actors " +
                "JOIN FETCH m.genres " +
                "WHERE s.cinema = :cinema AND s.date BETWEEN :fromTime AND :toTime " +
                "ORDER BY m.title ASC ");
        query.setParameter("cinema", cinema);
        query.setParameter("fromTime", date.atTime(LocalTime.MIDNIGHT));
        query.setParameter("toTime", date.atTime(LocalTime.MAX));
        List<Movie> movies = query.getResultList();
        return movies.stream().map(MovieDto::new).collect(Collectors.toList());
    }
}