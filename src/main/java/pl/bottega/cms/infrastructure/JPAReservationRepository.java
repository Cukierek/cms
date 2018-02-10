package pl.bottega.cms.infrastructure;

import org.springframework.stereotype.Component;
import pl.bottega.cms.model.GenericJpaRepository;
import pl.bottega.cms.model.Reservation;
import pl.bottega.cms.model.ReservationRepository;

import javax.persistence.EntityManager;
import java.util.List;


@Component
public class JPAReservationRepository extends GenericJpaRepository<Reservation> implements ReservationRepository {
	@Override
	public List<Reservation> getReservations(Long showId) {
		return (List<Reservation>) entityManager
				.createQuery("FROM Reservation r where r.showId = :showId")
				.setParameter("showId", showId)
				.getResultList();
	}
}
