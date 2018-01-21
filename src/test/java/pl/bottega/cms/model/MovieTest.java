package pl.bottega.cms.model;

import org.junit.Assert;
import org.junit.Test;
import pl.bottega.cms.domain.Movie;
import pl.bottega.cms.domain.commands.CreateMovieCommand;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MovieTest {

        @Test
        public void shouldCreateMovieFromCommand(){

        	// GIVEN
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

            // WHEN
            Movie movie = new Movie(cmc);

            // THEN
            Assert.assertEquals(cmc.getTitle(), movie.getTitle());
            Assert.assertEquals(cmc.getMinAge(), movie.getMinAge());
            Assert.assertEquals(cmc.getActors(), movie.getActors());
            Assert.assertEquals(cmc.getGenres(), movie.getGenres());
            Assert.assertEquals(cmc.getDescription(), movie.getDescription());
        }



}
