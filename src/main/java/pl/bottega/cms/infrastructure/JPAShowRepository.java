package pl.bottega.cms.infrastructure;

import org.springframework.stereotype.Component;
import pl.bottega.cms.model.Show;
import pl.bottega.cms.model.ShowRepository;

import javax.persistence.EntityManager;

@Component
public class JPAShowRepository implements ShowRepository {

	private EntityManager entityManager;

	public JPAShowRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void save(Show show) {
		entityManager.persist(show);
	}
}
