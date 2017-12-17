package pl.bottega.cms.infrastructure;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.support.TransactionTemplate;
import pl.bottega.cms.acceptance.AcceptanceTest;
import pl.bottega.cms.api.CinemaDto;
import pl.bottega.cms.api.CinemaFinder;
import pl.bottega.cms.domain.Cinema;
import pl.bottega.cms.domain.commands.CreateCinemaCommand;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class JPACinemaFinderTest extends AcceptanceTest {

    @Autowired
    private TransactionTemplate tt;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private CinemaFinder cinemaFinder;


    private void createCinemas() {
        tt.execute(c -> {
            entityManager.persist(new Cinema(new CreateCinemaCommand("Felicty", "Lublin")));
            entityManager.persist(new Cinema(new CreateCinemaCommand("Plaza", "Lublin")));
            entityManager.persist(new Cinema(new CreateCinemaCommand("Złote tarasy", "Warszawa")));
            return null;
        });
    }

    @Test
    public void shouldListAllCinemas() {
        //given
        createCinemas();

        //then
        List<CinemaDto> result = cinemaFinder.getAll();

        //when
        assertEquals(3, result.size());
    }

}