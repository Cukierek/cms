package pl.bottega.cms.model;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.bottega.cms.application.AddMovieHandler;
import pl.bottega.cms.model.commands.AddMovieCommand;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MovieTest {





//	@Test
//	public void shouldCreateMovie() {
//		// GIVEN
//		AddMovieCommand amc = new AddMovieCommand();
//		Set<String> actors = new HashSet<String>(Arrays.asList("John Travolta", "Samuel L. Jackson"));
//		Set<String> genres = new HashSet<>(Arrays.asList("Komedia dramatyczna"));
//		String description = "Fajny film";
//		Integer minAge = 17;
//		String title = "Pulp Fiction3";
//
//		amc.setActors(actors);
//		amc.setDescription(description);
//		amc.setGenres(genres);
//		amc.setMinAge(minAge);
//		amc.setTitle(title);
//
//
//		// WHEN
//		addMovieHandler.handle(amc);
//
//
//		// THEN
////		Assert.assertEquals(cmc.getTitle(), movie.getTitle());
////		Assert.assertEquals(cmc.getMinAge(), movie.getMinAge());
////		Assert.assertEquals(cmc.getActors(), movie.getActors());
////		Assert.assertEquals(cmc.getGenres(), movie.getGenres());
////		Assert.assertEquals(cmc.getDescription(), movie.getDescription());
//	}
}
