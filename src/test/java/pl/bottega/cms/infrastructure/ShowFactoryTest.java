package pl.bottega.cms.infrastructure;

import org.apache.tomcat.jni.Local;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.support.TransactionTemplate;
import pl.bottega.cms.acceptance.AcceptanceTest;
import pl.bottega.cms.domain.*;
import pl.bottega.cms.domain.commands.CreateCinemaCommand;
import pl.bottega.cms.domain.commands.CreateMovieCommand;
import pl.bottega.cms.domain.commands.CreateShowsCommand;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ShowFactoryTest extends AcceptanceTest {

	@Autowired
	private TransactionTemplate tt;

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private ShowFactory showFactory;

	@Autowired
	private ShowRepository showRepository;

	private void createCinemas() {
		tt.execute(c -> {
			CreateCinemaCommand ccc = new CreateCinemaCommand();

			ccc.setName("Felicity");
			ccc.setCity("Lublin");
			entityManager.persist(new Cinema(ccc));

			ccc.setName("Plaza");
			ccc.setCity("Lublin");
			entityManager.persist(new Cinema(ccc));

			ccc.setName("Złote Tarasy");
			ccc.setCity("Warszawa");
			entityManager.persist(new Cinema(ccc));

			return null;
		});
	}

	private void createMovies() {
		tt.execute(c -> {
			CreateMovieCommand cmc = new CreateMovieCommand();

			cmc.setTitle("Pulp Fiction");
			cmc.setDescription("Cool story about 2 dudes having a hamburger.");
			cmc.setActors(new HashSet<>(Arrays.asList("John Travolta", "Samuel L. Jackson")));
			cmc.setGenres(new HashSet<>(Arrays.asList("Drama", "Fiction", "Criminal")));
			cmc.setMinAge(18);
			cmc.setLength(178);
			entityManager.persist(new Movie(cmc));

			cmc.setTitle("Dragonball: Evolution");
			cmc.setDescription("Abomination of a movie, created for sole purpose of pissing fans off");
			cmc.setActors(new HashSet<>(Arrays.asList("Justin \"Fartface\" Chatwin", "Emmy Rossum", "Jamie Chung")));
			cmc.setGenres(new HashSet<>(Arrays.asList("Fiction", "Fantasy", "Adaptation", "Crap")));
			cmc.setMinAge(13);
			cmc.setLength(85);
			entityManager.persist(new Movie(cmc));

			return null;
		});
	}

	private CreateShowsCommand createShows(String requestType) {
		switch (requestType) {
			case "dates" : {
				CreateShowsCommand csc = new CreateShowsCommand();
				csc.setCinemaId(1L);
				csc.setMovieId(1L);
				csc.setDates(new HashSet<LocalDateTime>(Arrays.asList(LocalDateTime.parse("2017,1,1, 10, 30)"),
						LocalDateTime.parse("2017/01/01 14:00"), LocalDateTime.parse("2017/01/01 18:30"),
						LocalDateTime.parse("2017/01/01 20:30"), LocalDateTime.parse("2017/01/01 22:15"))));
			}
			case "calendar" : {

			}
			default: return null;
		}
	}

	@Test
	public void shouldCreateShowsFromDatesRequest() {
		// given
		createCinemas();
		createMovies();
		CreateShowsCommand csc = createShows("dates");

		// when
		Collection<Show> shows = showFactory.createShows(csc);

		// then
		assertEquals(3, shows.size());
	}



}
