package pl.bottega.cms.application;

import pl.bottega.cms.model.ReservationRepository;
import pl.bottega.cms.model.Show;
import pl.bottega.cms.model.commands.CalculatePriceCommand;
import pl.bottega.cms.model.commands.Command;
import pl.bottega.cms.model.commands.ValidationErrors;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CalculatePriceHandler implements Handler <CalculatePriceCommand, PriceDto> {

	ReservationRepository reservationRepository;
	private ValidationErrors validationErrors;

	@Override
	@Transactional
	public PriceDto handle(CalculatePriceCommand cmd) {
//		Show show = reservationRepository.get(cmd.getShowId());

		List<Show> shows = new ArrayList<>();

//		Movie movie = new Movie(cmd);
//		movieRepository.save(movie);

		return null;
	}

	@Override
	public Class<? extends Command> getSupportedCommandClass() {
		return CalculatePriceCommand.class;
	}
}
