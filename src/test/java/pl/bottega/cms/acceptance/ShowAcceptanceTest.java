package pl.bottega.cms.acceptance;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.support.TransactionTemplate;
import pl.bottega.cms.model.*;
import pl.bottega.cms.model.commands.CreateCinemaCommand;
import pl.bottega.cms.model.commands.CreateMovieCommand;
import pl.bottega.cms.model.commands.CreateShowsCommand;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ShowAcceptanceTest extends AcceptanceTest {

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

	private CreateShowsCommand createShows(String parameterSet) {
		CreateShowsCommand csc = new CreateShowsCommand();
		switch (parameterSet) {
			case "dates": {
				csc.setCinemaId(1L);
				csc.setMovieId(1L);
				csc.setDates(new HashSet<LocalDateTime>(
						Arrays.asList(
								LocalDateTime.parse("2019-01-01T10:30"),
								LocalDateTime.parse("2019-01-01T14:00"),
								LocalDateTime.parse("2019-01-01T18:30"),
								LocalDateTime.parse("2019-01-01T20:30"),
								LocalDateTime.parse("2019-01-01T22:15")
						)
				));
				return csc;
			}
			case "calendar": {
				csc.setCinemaId(1L);
				csc.setMovieId(1L);
				ShowsCalendar showsCalendar = new ShowsCalendar();
				showsCalendar.setFromDate(LocalDateTime.parse("2019-01-01T10:30"));
				showsCalendar.setUntilDate(LocalDateTime.parse("2019-01-15T22:30"));
				showsCalendar.setHours(new HashSet<>(Arrays.asList(LocalTime.parse("12:30"))));
				showsCalendar.setWeekDays(new HashSet<>(Arrays.asList("Wednesday", "Thursday")));
				csc.setCalendar(showsCalendar);
				return csc;
			}
			default:
				return null;
		}
	}

	@Test
	public void shouldCreateShowsFromDatesRequest() {
		// GIVEN
		createCinemas();
		createMovies();
		CreateShowsCommand csc = createShows("dates");

		// WHEN
		Collection<Show> shows = showFactory.createShows(csc);

		// THEN
		assertEquals(5, shows.size());
	}

	@Test
	public void shouldCreateShowsFromCalendarRequest() {
		// GIVEN
		createCinemas();
		createMovies();
		CreateShowsCommand csc = createShows("calendar");

		// WHEN
		Collection<Show> shows = showFactory.createShows(csc);

		// THEN
		assertEquals(4, shows.size());
	}

}
