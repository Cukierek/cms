package pl.bottega.cms.infrastructure;

import org.springframework.stereotype.Component;
import pl.bottega.cms.domain.Movie;
import pl.bottega.cms.domain.MovieRepository;

import javax.persistence.EntityManager;
@Component
public class JPAMovieRepository implements MovieRepository {

    private EntityManager entityManager;

    public JPAMovieRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Movie movie) {
        entityManager.persist(movie);
    }
}
