package pl.bottega.cms.acceptance;

import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class AcceptanceTest {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private TransactionTemplate tt;

	@After
	public void cleanUp() {
		tt.execute((c) -> {
			em.createNativeQuery("DELETE FROM cinemas").executeUpdate();
			em.createNativeQuery("DELETE FROM movie_actors").executeUpdate();
			em.createNativeQuery("DELETE FROM movie_genres").executeUpdate();
			em.createNativeQuery("DELETE FROM movies").executeUpdate();
			em.createNativeQuery("DELETE FROM shows").executeUpdate();
			em.createNativeQuery("ALTER TABLE cinemas AUTO_INCREMENT = 1").executeUpdate();
			em.createNativeQuery("ALTER TABLE movie_actors AUTO_INCREMENT = 1").executeUpdate();
			em.createNativeQuery("ALTER TABLE movie_genres AUTO_INCREMENT = 1").executeUpdate();
			em.createNativeQuery("ALTER TABLE movies AUTO_INCREMENT = 1").executeUpdate();
			em.createNativeQuery("ALTER TABLE shows AUTO_INCREMENT = 1").executeUpdate();
			return null;
		});
	}

}
