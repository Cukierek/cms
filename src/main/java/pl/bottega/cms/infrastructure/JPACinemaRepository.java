package pl.bottega.cms.infrastructure;

import org.springframework.stereotype.Component;
import pl.bottega.cms.domain.Cinema;
import pl.bottega.cms.domain.CinemaRepository;

import javax.persistence.EntityManager;

@Component
public class JPACinemaRepository implements CinemaRepository {

    private EntityManager entityManager;

    public JPACinemaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(Cinema cinema) {
        entityManager.persist(cinema);
    }

	@Override
	public Cinema get(Long id) {
		Cinema movie = entityManager.find(Cinema.class, id);
		if(movie == null)
			throw new NoSuchEntityException();
		return movie;
	}
}
