package pl.bottega.cms.acceptance;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.bottega.cms.model.Movie;
import pl.bottega.cms.model.MovieRepository;
import pl.bottega.cms.model.TicketPrices;
import pl.bottega.cms.model.commands.CreateMovieCommand;

import java.math.BigDecimal;
import java.util.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TicketPriceAcceptanceTest extends AcceptanceTest {

    @Autowired
    MovieRepository movieRepository;

    TicketPrices ticketPrices;


    CreateMovieCommand cmc = new CreateMovieCommand();
    Set<String> actors = new HashSet<>(Arrays.asList("John Travolta", "Samuel L. Jackson"));
    Set<String> genres = new HashSet<>(Arrays.asList("Komedia dramatyczna"));
    String description = "Fajny film";
    Integer minAge = 17;
    String title = "Pulp Fiction";
    Map<String, BigDecimal> prices = new HashMap<String, BigDecimal>();


    @Test
    public void shouldAddNewPriceToMovie() {
        // GIVEN
        cmc.setActors(actors);
        cmc.setDescription(description);
        cmc.setGenres(genres);
        cmc.setMinAge(minAge);
        cmc.setTitle(title);
        prices.put("regular", BigDecimal.valueOf(9.99));
        prices.put("student", BigDecimal.valueOf(99.99));
        prices.put("halfprice", BigDecimal.valueOf(5.00));
        prices.put("kids", BigDecimal.valueOf(2.00));

        TicketPrices ticketPrices = new TicketPrices(prices);

        //When
        Movie movie = new Movie(cmc);
        movie.setPrices(ticketPrices);


        //Then
        Assert.assertEquals(ticketPrices, movie.getTicketPrices());

    }


}
