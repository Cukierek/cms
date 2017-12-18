package pl.bottega.cms.acceptance;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.bottega.cms.api.CinemaDto;
import pl.bottega.cms.api.CinemaFinder;
import pl.bottega.cms.api.CreateCinemaHandler;
import pl.bottega.cms.domain.commands.CommandInvalidException;
import pl.bottega.cms.domain.commands.CreateCinemaCommand;

import static org.assertj.core.api.Java6Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CreateCinemaTest extends AcceptanceTest{

    @Autowired
    private CreateCinemaHandler createCinemaHandler;

    @Autowired
    private CinemaFinder cinemaFinder;

    private CreateCinemaCommand createCinemaCommand;

    @Before
    public void createCinema(){
        createCinemaCommand = new CreateCinemaCommand();
        createCinemaCommand.setCity("Lublin");
        createCinemaCommand.setName("Felicity");
    }

    @Test
    public void shouldCreateCinema(){
        //when
        createCinemaHandler.handle(createCinemaCommand);

        //then
        CinemaDto cinemaDto = cinemaFinder.getAll().get(0);
        assertThat(createCinemaCommand.getCity()).isEqualTo(cinemaDto.getCity());
        assertThat(createCinemaCommand.getName()).isEqualTo(cinemaDto.getName());
    }

    @Test(expected = CommandInvalidException.class)
    public void shouldNotAllowToPersistDuplicateCinema(){
        //given
        createCinemaHandler.handle(createCinemaCommand);

        //when
        createCinemaHandler.handle(createCinemaCommand);
    }
}
