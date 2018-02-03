package pl.bottega.cms.acceptance;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.support.TransactionTemplate;
import pl.bottega.cms.application.CreateMovieHandler;
import pl.bottega.cms.model.Movie;
import pl.bottega.cms.model.MovieRepository;
import pl.bottega.cms.model.commands.CommandInvalidException;
import pl.bottega.cms.model.commands.CreateMovieCommand;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MovieAcceptanceTest extends AcceptanceTest {

	@Autowired
	private CreateMovieHandler createMovieHandler;

	@Autowired
	private MovieRepository movieRepository;

	@Test
	public void shouldCreateMovie() {
		// GIVEN
		CreateMovieCommand cmc = new CreateMovieCommand();
		Set<String> actors = new HashSet<>(Arrays.asList("John Travolta", "Samuel L. Jackson"));
		Set<String> genres = new HashSet<>(Arrays.asList("Komedia dramatyczna"));
		String description = "Fajny film";
		Integer minAge = 17;
		Integer length = 180;
		String title = "Pulp Fiction";

		cmc.setActors(actors);
		cmc.setDescription(description);
		cmc.setGenres(genres);
		cmc.setMinAge(minAge);
		cmc.setTitle(title);
		cmc.setLength(length);

		// WHEN
		Void nothing = createMovieHandler.handle(cmc);

		// THEN
		Movie movie = movieRepository.get(1L);

		Assert.assertEquals(cmc.getTitle(), movie.getTitle());
		Assert.assertEquals(cmc.getMinAge(), movie.getMinAge());
		Assert.assertEquals(cmc.getActors(), movie.getActors());
		Assert.assertEquals(cmc.getGenres(), movie.getGenres());
		Assert.assertEquals(cmc.getDescription(), movie.getDescription());
	}

	@Test(expected = CommandInvalidException.class)
	public void shouldNotCreateMovieWithNoTitle() {
		// GIVEN
		CreateMovieCommand cmc = new CreateMovieCommand();
		Set<String> actors = new HashSet<>(Arrays.asList("John Travolta", "Samuel L. Jackson"));
		Set<String> genres = new HashSet<>(Arrays.asList("Komedia dramatyczna"));
		String description = "Fajny film";
		Integer minAge = 17;
		Integer length = 180;
		String title = "";

		cmc.setDescription(description);
		cmc.setActors(actors);
		cmc.setGenres(genres);
		cmc.setMinAge(minAge);
		cmc.setTitle(title);
		cmc.setLength(length);

		// WHEN
		createMovieHandler.handle(cmc);
	}
}
