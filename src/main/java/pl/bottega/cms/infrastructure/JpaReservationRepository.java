package pl.bottega.cms.infrastructure;

import org.springframework.stereotype.Component;
import pl.bottega.cms.model.GenericJpaRepository;
import pl.bottega.cms.model.Reservation;
import pl.bottega.cms.model.ReservationRepository;

import java.util.List;



@Component
public class JpaReservationRepository extends GenericJpaRepository<Reservation> implements ReservationRepository {


    @Override
    public List<Reservation> getReservations(Long showId) {
        List<Reservation> reservations = entityManager.createQuery("select r " +
                "From Reservation r where r.showId = :showId")
                .setParameter("showId", showId)
                .getResultList();
        return reservations;
    }
}
