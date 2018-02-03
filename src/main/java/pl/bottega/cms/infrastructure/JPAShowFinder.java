package pl.bottega.cms.infrastructure;

import pl.bottega.cms.application.ShowDto;
import pl.bottega.cms.application.ShowFinder;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

public class JPAShowFinder implements ShowFinder {

	private EntityManager entityManager;

	public JPAShowFinder(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<ShowDto> get(Integer cinemaId, LocalDate date) {
		String jpql = "SELECT NEW pl.bottega.cms.application.ShowDto(s.id, s.name, s.city) FROM Show s" +
				"JOIN FETCH Cinema c" +
				"WHERE c.id = :cinemaId AND s.date = :date";
		Query query = entityManager.createQuery(jpql);
		List<ShowDto> result = query.getResultList();
		if (result.size() == 0)
			throw new NoSuchEntityException();
		return result;
	}
}
