package pl.bottega.cms.acceptance;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.bottega.cms.application.CreateCinemaHandler;
import pl.bottega.cms.model.Cinema;
import pl.bottega.cms.model.CinemaRepository;
import pl.bottega.cms.model.commands.CommandInvalidException;
import pl.bottega.cms.model.commands.CreateCinemaCommand;

import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CinemaAcceptanceTest extends AcceptanceTest {

	@Autowired
	CreateCinemaHandler createCinemaHandler;

	@Autowired
	CinemaRepository cinemaRepository;

	@Test
	public void shouldCreateCinema() {
		// GIVEN
		String givenCinemaName = "Plaza";
		String givenCinemaCity = "Lublin";
		CreateCinemaCommand cmc = new CreateCinemaCommand();
		cmc.setName(givenCinemaName);
		cmc.setCity(givenCinemaCity);

		// WHEN
		createCinemaHandler.handle(cmc);

		// THEN
		Optional<Cinema> actualCinemaOptional = cinemaRepository.findByNameAndCity(givenCinemaName, givenCinemaCity);
		String actualCinemaName = actualCinemaOptional.get().getName();
		String actualCinemaCity = actualCinemaOptional.get().getCity();
		Assert.assertEquals(true, actualCinemaOptional.isPresent());
		Assert.assertEquals(givenCinemaName, actualCinemaName);
		Assert.assertEquals(givenCinemaCity, actualCinemaCity);
	}

	@Test(expected = CommandInvalidException.class)
	public void shouldNotCreateSameCinemaTwice() {
		// GIVEN
		String givenCinemaName = "Plaza";
		String givenCinemaCity = "Lublin";
		CreateCinemaCommand cmc = new CreateCinemaCommand();
		cmc.setName(givenCinemaName);
		cmc.setCity(givenCinemaCity);

		// WHEN
		createCinemaHandler.handle(cmc);
		createCinemaHandler.handle(cmc);
	}

	@Test(expected = CommandInvalidException.class)
	public void shouldNotCreateCinemaWithEmptyParameters() {
		// GIVEN
		String givenCinemaName = "";
		String givenCinemaCity = "";
		CreateCinemaCommand cmc = new CreateCinemaCommand();
		cmc.setName(givenCinemaName);
		cmc.setCity(givenCinemaCity);

		// WHEN
		createCinemaHandler.handle(cmc);
	}
}
