package pl.bottega.cms.application;

import org.springframework.stereotype.Component;
import pl.bottega.cms.model.*;
import pl.bottega.cms.model.commands.Command;
import pl.bottega.cms.model.commands.CreateReservationCommand;
import pl.bottega.cms.model.commands.ValidationErrors;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Component
public class CreateReservationHandler implements Handler<CreateReservationCommand, Long> {

    private ShowRepository showRepository;
    private ReservationRepository reservationRepository;
    private ValidationErrors errors;


    public CreateReservationHandler(ShowRepository showRepository, ReservationRepository reservationRepository, ValidationErrors errors) {
        this.showRepository = showRepository;
        this.reservationRepository = reservationRepository;
        this.errors = errors;
    }

    @Override
    @Transactional
    public Long handle(CreateReservationCommand command) {
        validateTickets(command);
        Set<Reservation> reservations = reservationRepository.getReservations(command.getShowId());
        CinemaHall cinemaHall = new CinemaHall(reservations);


        return null;
    }

    private void validateTickets(CreateReservationCommand command) {
        validateNumberOfTickets(command);
        validateTicketsKinds(command);
//            validateKindsTicketsAvailabilty(errors, kind, command.getShowId());

    }

    private void validateNumberOfTickets(CreateReservationCommand command) {

        if (!(countNumberOfTickets(command) > 0))
            errors.add("ticket", "Shoud be at least one");

    }

    private int countNumberOfTickets(CreateReservationCommand command) {
        int count = 0;
        for (Ticket ticket : command.getTickets())
            count = +ticket.getCount();
        return count;
    }


    private void checkSeatsAreAvailable(Set<Seat> seats, CinemaHall cinemaHall) {
        for (Seat seat : seats) {
            if (!cinemaHall.checkSeatAvailability(seat))
                errors.add("seat", String.format("Place row: %d seat: %D is not available", seat.getRow(), seat.getSeatNumber()));
        }


    }

    @Override
    public Class<? extends Command> getSupportedCommandClass() {
        return CreateReservationCommand.class;
    }


    private void validateTicketsKinds(CreateReservationCommand command) {
        Set<Ticket> tickets = command.getTickets();
        Set<String> kinds = new HashSet<>();
        for (Ticket ticket : tickets) {
            String kind = ticket.getKind();
            kinds.add(kind);

        }

        if (tickets == null || kinds.size() != tickets.size())
            errors.add("tickets kinds", "can't be empty or be the same kind");

    }

    private void validateKindsTicketsAvailarelobilty(ValidationErrors errors, String kind, Long showId) {
        TicketPrices tp = showRepository.get(showId).getMovie().getTicketPrices();

        if (!(tp.getPrices().containsKey(kind)))
            errors.add("tickets kind", "Requested kinds of ticket are not available");

    }

}

