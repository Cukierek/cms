package pl.bottega.cms.application;

import org.springframework.stereotype.Component;
import pl.bottega.cms.model.*;
import pl.bottega.cms.model.commands.Command;
import pl.bottega.cms.model.commands.CommandInvalidException;
import pl.bottega.cms.model.commands.CreateReservationCommand;
import pl.bottega.cms.model.commands.ValidationErrors;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
        List<Reservation> reservations = reservationRepository.getReservations(command.getShowId());
        CinemaHall cinemaHall = new CinemaHall(reservations);


        return null;
    }

    private void validateTickets(CreateReservationCommand command) {
        validateNumberOfTickets(command);
        validateTicketsKinds(command);
        checkSeats(command);
	    // validateKindsTicketsAvailarelobilty(errors, kind, command.getShowId());

        if (errors.any())
            throw new CommandInvalidException(errors);

    }

    private void checkSeats(CreateReservationCommand command) {
        checkIfOneRow(command);
        checkIfNextTo(command);


    }

    private void checkIfNextTo(CreateReservationCommand command) {
        List<Seat> seats = new ArrayList<>(command.getSeats());
        seats.sort((o1, o2) -> {
            if (o1.getSeatNumber() == o2.getSeatNumber()) return 0;
            if (o1.getSeatNumber() > o2.getSeatNumber()) return 1;
            return -1;
        });

        for (int i = 1; i < seats.size(); i++) {
            if (seats.get(i - 1).getSeatNumber() < seats.get(i).getSeatNumber())
                errors.add("seat number", "Seats aren't next to others");
        }
        ;

    }

    private void checkIfOneRow(CreateReservationCommand command) {

        int rowNumber = command.getSeats().stream().findFirst().get().getRow();

        for (Seat seat : command.getSeats()) {
            if (seat.getRow() != rowNumber) {
                errors.add("row", "Not the same row");
            }

        }


    }

    private void validateNumberOfTickets(CreateReservationCommand command) {

        int numberOfTickets = countNumberOfTickets(command);
        if (!(numberOfTickets > 0) || numberOfTickets != countNumberOfSeats(command)) {
            errors.add("number of tickets", "Number must be equal to number of Seats and be more than 0");

        }


    }


    private int countNumberOfSeats(CreateReservationCommand command) {
        return command.getSeats().size();

    }

    private int countNumberOfTickets(CreateReservationCommand command) {
        int count = 0;
        for (Ticket ticket : command.getTickets())
            count += ticket.getCount();
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

