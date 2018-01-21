package pl.bottega.cms.infrastructure;

import org.springframework.stereotype.Component;
import pl.bottega.cms.domain.Cinema;
import pl.bottega.cms.domain.CinemaRepository;

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
