package pl.bottega.cms.infrastructure;

import org.springframework.stereotype.Component;
import pl.bottega.cms.application.CinemaDto;
import pl.bottega.cms.application.CinemaFinder;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class JPACinemaFinder implements CinemaFinder {

	private EntityManager entityManager;

	public JPACinemaFinder(EntityManager entityManager) {
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
				"WHERE c.name=:name AND c.city=:city";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("name", name);
		query.setParameter("city", city);
		CinemaDto result = (CinemaDto) query.getSingleResult();
		return null;
	}
}