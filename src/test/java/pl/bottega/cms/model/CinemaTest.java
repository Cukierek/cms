package pl.bottega.cms.model;

import org.junit.Assert;
import org.junit.Test;
import pl.bottega.cms.model.commands.CreateCinemaCommand;

public class CinemaTest {
	@Test
	public void shouldCreateCinemaFromCommand() {
		// GIVEN
		CreateCinemaCommand ccc = new CreateCinemaCommand();
		String city = "Lublin";
		String name = "Felicity";

		ccc.setCity(city);
		ccc.setName(name);

		// WHEN
		Cinema cinema = new Cinema(ccc);

		// THEN
		Assert.assertEquals(ccc.getCity(), cinema.getCity());
		Assert.assertEquals(ccc.getName(), cinema.getName());
	}
}
