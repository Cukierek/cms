package pl.bottega.cms.model;

import org.junit.Assert;
import org.junit.Test;
import pl.bottega.cms.model.commands.CreateCinemaCommand;
import pl.bottega.cms.model.commands.CreateMovieCommand;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ShowUnitTest {
	@Test
	public void shouldCreateShowFromCommand() {
		// GIVEN
		CreateCinemaCommand ccc = new CreateCinemaCommand();

		ccc.setName("Felicity");
		ccc.setCity("Lublin");

		CreateMovieCommand cmc = new CreateMovieCommand();

		Set<String> actors = new HashSet<String>(Arrays.asList("John Travolta", "Samuel L. Jackson"));
		Set<String> genres = new HashSet<>(Arrays.asList("Komedia dramatyczna"));
		String description = "Fajny film";
		Integer minAge = 17;
		String title = "Pulp Fiction";

		cmc.setActors(actors);
		cmc.setDescription(description);
		cmc.setGenres(genres);
		cmc.setMinAge(minAge);
		cmc.setTitle(title);

		Cinema cinema = new Cinema(ccc);
		Movie movie = new Movie(cmc);
		LocalDateTime date = LocalDateTime.parse("2017-01-01T20:30:00");

		// WHEN
		Show show = new Show(cinema, movie, date);

		// THEN
		Assert.assertEquals(cinema, show.getCinema());
		Assert.assertEquals(movie, show.getMovie());
		Assert.assertEquals(date, show.getDate());
	}
}
