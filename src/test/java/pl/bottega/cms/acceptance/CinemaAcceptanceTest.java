package pl.bottega.cms.acceptance;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.support.TransactionTemplate;
import pl.bottega.cms.application.CinemaDto;
import pl.bottega.cms.application.CinemaFinder;
import pl.bottega.cms.application.CreateCinemaHandler;
import pl.bottega.cms.infrastructure.NoSuchEntityException;
import pl.bottega.cms.model.Cinema;
import pl.bottega.cms.model.commands.Command;
import pl.bottega.cms.model.commands.CommandInvalidException;
import pl.bottega.cms.model.commands.CreateCinemaCommand;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CinemaAcceptanceTest extends AcceptanceTest {

	@Autowired
	private CreateCinemaHandler createCinemaHandler;

	@Autowired
	private CinemaFinder cinemaFinder;

	private CreateCinemaCommand createCinemaCommand;

	@Before
	public void prepareCinemaCommandAndCinemaHandler() {
		// GIVEN
		createCinemaCommand = new CreateCinemaCommand();
		createCinemaCommand.setName("Felicity");
		createCinemaCommand.setCity("Lublin");
	}

	@Test
	public void shouldSaveAllCinemas() {
		// GIVEN
		CreateCinemaCommand c1 = new CreateCinemaCommand("Felicity","Poznań");
		CreateCinemaCommand c2 = new CreateCinemaCommand("Plaza", "Gdynia");
		CreateCinemaCommand c3 = new CreateCinemaCommand("Złote Tarasy", "Warszawa");
		createCinemaHandler.handle(c1);
		createCinemaHandler.handle(c2);
		createCinemaHandler.handle(c3);

		// WHEN
		List<CinemaDto> result = cinemaFinder.getAll();

		// THEN
		assertEquals(3, result.size());
	}

	@Test(expected = NoSuchEntityException.class)
	public void shouldNotFindCinemasWhileNoCinemaInDatabase() {
		// WHEN
		List<CinemaDto> result = cinemaFinder.getAll();
	}


	@Test
	public void shouldCreateCinema() {
		// WHEN
		createCinemaHandler.handle(createCinemaCommand);

		// THEN
		CinemaDto cinemaDto = cinemaFinder.getAll().get(0);
		assertThat(createCinemaCommand.getName()).isEqualTo(cinemaDto.getName());
		assertThat(createCinemaCommand.getCity()).isEqualTo(cinemaDto.getCity());
	}

	@Test(expected = CommandInvalidException.class)
	public void shouldNotAllowToPersistDuplicateCinema() {
		// WHEN
		createCinemaHandler.handle(createCinemaCommand);
		createCinemaHandler.handle(createCinemaCommand);
	}

	@Test(expected = CommandInvalidException.class)
	public void shouldNotAllowToPersistCinemaWithEmptyParameters() {
		// GIVEN
		createCinemaCommand.setName("");
		createCinemaCommand.setCity("");

		// WHEN
		createCinemaHandler.handle(createCinemaCommand);
	}
}
