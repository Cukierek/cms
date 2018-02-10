package pl.bottega.cms.application;

import pl.bottega.cms.model.Reservation;
import pl.bottega.cms.model.ReservationRepository;
import pl.bottega.cms.model.Show;
import pl.bottega.cms.model.ShowRepository;
import pl.bottega.cms.model.commands.CalculatePricesCommand;
import pl.bottega.cms.model.commands.Command;
import pl.bottega.cms.model.commands.ValidationErrors;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public class CalculatePricesHandler implements Handler <CalculatePricesCommand, Receipt> {

	private ReservationRepository reservationRepository;
	private ShowRepository showRepository;
	private ValidationErrors validationErrors;

	@Override
	@Transactional
	public Receipt handle(CalculatePricesCommand cmd) {
//		List<Reservation> reservations = reservationRepository.getReservations(cmd.getShowId());
//		reservations.stream().forEach(reservation -> {
//			if (reservation.getTickets().containsAll(cmd.getTickets())) {
//
//			}
//		});
		Show show = showRepository.get(cmd.getShowId());
		return show.calculate(cmd);
	}

	@Override
	public Class<? extends Command> getSupportedCommandClass() {
		return CalculatePricesCommand.class;
	}
}
