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
        public void shouldCreateMovie(){

            CreateMovieCommand cmc = new CreateMovieCommand();
            Set<String > actors = new HashSet<String>(Arrays.asList("John Travolta"));
            Set<String> genres = new HashSet<>(Arrays.asList("Komedia dramatyczna"));
            String description = "Fajny film";
            Integer minAge = 17;
            String title = "Pulp Fiction";

            cmc.setActors(actors);
            cmc.setDescription(description);
            cmc.setGenres(genres);
            cmc.setMinAge(minAge);
            cmc.setTitle(title);

            Movie movie = new Movie(cmc);

            Assert.assertEquals(title, movie.getTitle());
            Assert.assertEquals(minAge, movie.getMinAge());
            Assert.assertEquals(actors, movie.getActors());
            Assert.assertEquals(genres, movie.getGenres());
            Assert.assertEquals(description, movie.getDescription());

        }



}
