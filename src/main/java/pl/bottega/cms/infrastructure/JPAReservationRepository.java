package pl.bottega.cms.infrastructure;

import org.springframework.stereotype.Component;
import pl.bottega.cms.model.Reservation;
import pl.bottega.cms.model.ReservationRepository;

import javax.persistence.EntityManager;
import java.util.List;


@Component
public class JPAReservationRepository implements ReservationRepository {

    EntityManager entityManager;

    public JPAReservationRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(Reservation reservation) {

        entityManager.persist(reservation);

    }

    @Override
    public List<Reservation> getReservations(Long showId) {
        return (List<Reservation>) entityManager
                .createQuery("FROM Reservation r where r.showId = :showId")
                .setParameter("showId", showId)
                .getResultList();


    }
}
