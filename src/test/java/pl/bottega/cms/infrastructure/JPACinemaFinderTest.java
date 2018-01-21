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
            CreateCinemaCommand c1 = new CreateCinemaCommand("Felicity","Lublin");
            CreateCinemaCommand c2 = new CreateCinemaCommand("Plaza", "Lublin");
            CreateCinemaCommand c3 = new CreateCinemaCommand("Złote Tarasy", "Warszawa");
            entityManager.persist(new Cinema(c1));
            entityManager.persist(new Cinema(c2));
            entityManager.persist(new Cinema(c3));
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

    @Test(expected = NoSuchEntityException.class)
    public void  shouldNotFindCinemasWhileNoCinemaInDatabase() {
        //when
        List<CinemaDto> result = cinemaFinder.getAll();

    }

}