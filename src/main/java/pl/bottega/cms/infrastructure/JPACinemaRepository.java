package pl.bottega.cms.infrastructure;

import org.springframework.stereotype.Component;
import pl.bottega.cms.model.Cinema;
import pl.bottega.cms.model.CinemaRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.Optional;

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
		Cinema cinema = entityManager.find(Cinema.class, id);
		if(cinema == null)
			throw new NoSuchEntityException();
		return cinema;
	}

	@Override
    public Optional<Cinema> findByNameAndCity(String name, String city) {
        try {
            Query query = entityManager.createQuery("FROM Cinema c WHERE c.name = :name AND c.city = :city")
                    .setParameter("name", name)
                    .setParameter("city", city);
            return Optional.of((Cinema) query.getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }
}
