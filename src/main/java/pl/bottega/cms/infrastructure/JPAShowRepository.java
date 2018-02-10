package pl.bottega.cms.infrastructure;

import org.springframework.stereotype.Component;
import pl.bottega.cms.model.GenericJpaRepository;
import pl.bottega.cms.model.Show;
import pl.bottega.cms.model.ShowRepository;

import javax.persistence.EntityManager;

@Component
public class JPAShowRepository extends GenericJpaRepository<Show> implements ShowRepository {
}

