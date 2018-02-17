package pl.bottega.cms.infrastructure;

import org.springframework.stereotype.Component;
import pl.bottega.cms.model.GenericJpaRepository;
import pl.bottega.cms.model.Movie;
import pl.bottega.cms.model.MovieRepository;

import javax.persistence.EntityManager;

@Component
public class JpaMovieRepository extends GenericJpaRepository<Movie> implements MovieRepository {
}
