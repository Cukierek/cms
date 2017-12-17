package pl.bottega.cms.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.bottega.cms.api.CinemaDto;
import pl.bottega.cms.api.CinemaFinder;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Component
public class JPACinemaFinder implements CinemaFinder {

    private EntityManager entityManager;

    public JPACinemaFinder(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<CinemaDto> getAll() {
        String jpql = "SELECT NEW pl.bottega.cms.api.CinemaDto(c.id, c.name, c.city) FROM Cinema c";
        Query query = entityManager.createQuery(jpql);
        return query.getResultList();
    }
}
