package pl.bottega.cms.infrastructure;

import org.springframework.stereotype.Component;
import pl.bottega.cms.application.CinemaDto;
import pl.bottega.cms.application.CinemaFinder;
import pl.bottega.cms.application.CinemaHallDto;
import pl.bottega.cms.model.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Component
public class JpaCinemaFinder implements CinemaFinder {


	private ReservationRepository reservationRepository;
	private ShowRepository showRepository;
	private EntityManager entityManager;

	public JpaCinemaFinder(ReservationRepository reservationRepository, ShowRepository showRepository, EntityManager entityManager) {
		this.reservationRepository = reservationRepository;
		this.showRepository = showRepository;
		this.entityManager = entityManager;
	}

	@Override
	public List<CinemaDto> getAll() {
		String jpql = "SELECT NEW pl.bottega.cms.application.CinemaDto(c.id, c.name, c.city) FROM Cinema c";
		Query query = entityManager.createQuery(jpql);
		List<CinemaDto> result = query.getResultList();
		return result;
	}

	@Override
	public CinemaDto findByCityAndName(String city, String name) {
		String jpql =
				"SELECT NEW pl.bottega.cms.application.CinemaDto(c.id, c.name, c.city) " +
				"FROM Cinema c " +
				"WHERE c.name LIKE :name AND c.city LIKE :city";
		Query query = entityManager.createQuery(jpql).setParameter("name", name).setParameter("city", city);
		CinemaDto result = (CinemaDto) query.getSingleResult();
		return result;
	}

	@Override
	public CinemaHallDto getSeats(Long showId) {
		showRepository.get(showId);
		List<Reservation> reservations = (List<Reservation>) reservationRepository.get(showId);
		CinemaHall cinemaHall = new CinemaHall(reservations);
		return cinemaHall.getSeatsOccupation();
	}
}