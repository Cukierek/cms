package pl.bottega.cms.acceptance;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.bottega.cms.application.CinemaDto;
import pl.bottega.cms.application.CinemaFinder;
import pl.bottega.cms.application.CreateCinemaHandler;
import pl.bottega.cms.model.Cinema;
import pl.bottega.cms.model.CinemaRepository;
import pl.bottega.cms.model.commands.CommandInvalidException;
import pl.bottega.cms.model.commands.CreateCinemaCommand;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CinemaAcceptanceTest extends AcceptanceTest {

	@Autowired
	CreateCinemaHandler createCinemaHandler;

	@Autowired
	CinemaRepository cinemaRepository;

	@Autowired
	CinemaFinder cinemaFinder;

	private CreateCinemaCommand buildCinemaCommand(String name, String city) {
		CreateCinemaCommand cmc = new CreateCinemaCommand();
		cmc.setName(name);
		cmc.setCity(city);
		return cmc;
	}

	private void putCinema(String name, String city) {
		createCinemaHandler.handle(buildCinemaCommand(name, city));
	}

	@Test
	public void shouldCreateCinema() {
		// GIVEN
		String givenCinemaName = "Felicity";
		String givenCinemaCity = "Lublin";
		CreateCinemaCommand cmc = buildCinemaCommand(givenCinemaName, givenCinemaCity);

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
		String givenCinemaName = "Felicity";
		String givenCinemaCity = "Lublin";
		CreateCinemaCommand cmc = buildCinemaCommand(givenCinemaName, givenCinemaCity);

		// WHEN
		createCinemaHandler.handle(cmc);
		createCinemaHandler.handle(cmc);
	}

	@Test(expected = CommandInvalidException.class)
	public void shouldNotCreateCinemaWithEmptyParameters() {
		// GIVEN
		String givenCinemaName = "";
		String givenCinemaCity = "";
		CreateCinemaCommand cmc = buildCinemaCommand(givenCinemaName, givenCinemaCity);

		// WHEN
		createCinemaHandler.handle(cmc);
	}

	public void shouldGetEmptyCinemaList() {
		// GIVEN
		// nothing

		// WHEN
		List<CinemaDto> actualCinemas = cinemaFinder.getAll();

		// THEN
		Assert.assertEquals(0, actualCinemas.size());
		Assert.assertEquals(true, actualCinemas.isEmpty());
	}

	@Test
	public void shouldGetAllCinemas() {
		// GIVEN
		putCinema("Felicity", "Lublin");
		putCinema("Plaza", "Lublin");
		putCinema("Złote Tarasy", "Warszawa");
		putCinema("Cinema City", "Poznań");

		// WHEN
		List<CinemaDto> actualCinemas = cinemaFinder.getAll();

		// THEN
		List<CinemaDto> expectedCinemas = Arrays.asList(
				new CinemaDto(1L, "Felicity","Lublin"),
				new CinemaDto(2L, "Plaza", "Lublin"),
				new CinemaDto(3L, "Złote Tarasy", "Warszawa"),
				new CinemaDto(4L, "Cinema City", "Poznań")
		);
		Assert.assertEquals(expectedCinemas.size(), actualCinemas.size());
		Assert.assertEquals(expectedCinemas.get(0).getId(), actualCinemas.get(0).getId());
		Assert.assertEquals(expectedCinemas.get(0).getName(), actualCinemas.get(0).getName());
		Assert.assertEquals(expectedCinemas.get(0).getCity(), actualCinemas.get(0).getCity());
		Assert.assertEquals(expectedCinemas.get(1).getId(), actualCinemas.get(1).getId());
		Assert.assertEquals(expectedCinemas.get(1).getName(), actualCinemas.get(1).getName());
		Assert.assertEquals(expectedCinemas.get(1).getCity(), actualCinemas.get(1).getCity());
		Assert.assertEquals(expectedCinemas.get(2).getId(), actualCinemas.get(2).getId());
		Assert.assertEquals(expectedCinemas.get(2).getName(), actualCinemas.get(2).getName());
		Assert.assertEquals(expectedCinemas.get(2).getCity(), actualCinemas.get(2).getCity());
		Assert.assertEquals(expectedCinemas.get(3).getId(), actualCinemas.get(3).getId());
		Assert.assertEquals(expectedCinemas.get(3).getName(), actualCinemas.get(3).getName());
		Assert.assertEquals(expectedCinemas.get(3).getCity(), actualCinemas.get(3).getCity());
	}
}
