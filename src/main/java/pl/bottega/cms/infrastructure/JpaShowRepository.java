package pl.bottega.cms.infrastructure;

import org.springframework.stereotype.Component;
import pl.bottega.cms.model.GenericJpaRepository;
import pl.bottega.cms.model.Show;
import pl.bottega.cms.model.ShowRepository;

@Component
public class JpaShowRepository extends GenericJpaRepository<Show> implements ShowRepository {


    @Override
    public boolean checkIfExists(Long showId) {
        Long count = (Long) entityManager.createQuery("select count(s.id) from Show s where s.id = :showId")
                .setParameter("showId", showId).getSingleResult();
        return count == 1;
    }
}

