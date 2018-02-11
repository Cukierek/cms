package pl.bottega.cms.model;


import pl.bottega.cms.application.CinemaHallDto;
import pl.bottega.cms.model.commands.CommandInvalidException;
import pl.bottega.cms.model.commands.CreateReservationCommand;
import pl.bottega.cms.model.commands.ValidationErrors;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class CinemaHall {

    private final Integer ROWS = 10;
    private final Integer SEATS = 15;

    private CreateReservationCommand createReservationCommand;
    private ShowRepository showRepository;
    private ValidationErrors errors;


    boolean[][] seats = new boolean[ROWS][SEATS];

    public CinemaHall(List<Reservation> currentReservations, CreateReservationCommand createReservationCommand, ValidationErrors errors, ShowRepository showRepository) {
        this.createReservationCommand = createReservationCommand;
        this.errors = errors;
        this.showRepository = showRepository;
        currentReservations.forEach(this::getSeatsFromReservation);
    }

    public CinemaHall(List<Reservation> reservations){
        reservations.forEach(this::getSeatsFromReservation);
    }


    void getSeatsFromReservation(Reservation reservation) {
        reservation.getSeats().forEach(this::reserveSeat);
    }


    void reserveSeat(Seat seat) {
        seats[seat.getRow() - 1][seat.getSeatNumber() - 1] = true;

    }

    public void validateTickets() {
        validateTicketKindsAvailability();
        validateTicketsKindsUniqueness();
        validateNumberOfTickets();
        checkIfOneRow();
        checkIfNextTo();
        checkSeatsAreAvailable();


        if (errors.any())
            throw new CommandInvalidException(errors);

    }


    private void validateNumberOfTickets() {
        int numberOfTickets = countNumberOfTickets();
        if (numberOfTickets != countNumberOfSeats()) {
            errors.add("number of tickets", "Number must be equal to number of Seats");

        }

    }

    private int countNumberOfSeats() {
        return createReservationCommand.getSeats().size();

    }

    private int countNumberOfTickets() {
        int count = 0;
        for (Ticket ticket : createReservationCommand.getTickets()) {
            if (ticket.getCount() > 0) {
                count += ticket.getCount();
            } else {
                errors.add("number of tickets", "must be more then 0");
            }
        }
        return count;
    }

    private void validateTicketsKindsUniqueness() {
        Set<Ticket> tickets = createReservationCommand.getTickets();
        Set<String> kinds = new HashSet<>();
        for (Ticket ticket : tickets) {
            String kind = ticket.getKind();
            kinds.add(kind);
        }

        if (tickets == null || kinds.size() != tickets.size())
            errors.add("tickets kinds", "can't be empty or be the same kind");

    }


    private void checkIfOneRow() {
        int rowNumber = createReservationCommand.getSeats().stream().findFirst().get().getRow();
        for (Seat seat : createReservationCommand.getSeats()) {
            if (seat.getRow() != rowNumber) {
                errors.add("row", "Not the same row");
            }
        }

    }

    private void checkIfNextTo() {

        Integer[] seatNumbers = new Integer[createReservationCommand.getSeats().size()];
        int i = 0;
        for (Seat seat : createReservationCommand.getSeats()) {
            seatNumbers[i] = seat.getSeatNumber();
            i++;
        }
        Arrays.sort(seatNumbers);

        for (i = 1; i < seatNumbers.length; i++) {
            if (seatNumbers[i - 1] + 1 != seatNumbers[i])
                errors.add("seat numbers", "Seats aren't next to others");
        }


    }

    private void validateTicketKindsAvailability() {

        TicketPrices tp = showRepository.get(createReservationCommand.getShowId()).getMovie().getTicketPrices();

        for (Ticket t : createReservationCommand.getTickets()) {
            if (!(t.getKind().isEmpty())) {
                if (!(tp.getPrices().containsKey(t.kind)))
                    errors.add("tickets kind", String.format("%s tickets are not available", t.kind));

            }else{
                errors.add("ticket kind", "Can't be empty");
            }
        }
    }


    private void checkSeatsAreAvailable() {
        for (Seat seat : createReservationCommand.getSeats()) {
            if (!checkSeatAvailability(seat))
                errors.add("seat", String.format("Place row: %d seat: %d is not available", seat.getRow(), seat.getSeatNumber()));
        }


    }


    public boolean checkSeatAvailability(Seat seat) {
        if (seat.getRow() > ROWS || seat.getSeatNumber() > SEATS)
            return false;
        if (seats[seat.getRow() - 1][seat.getSeatNumber() - 1])
            return false;

        return true;

    }

    public CinemaHallDto getSeatsOccupation() {
        Set<Seat> free = new HashSet<>();
        Set<Seat> occupied = new HashSet<>();
        setSeatOccupation(free, occupied);
        return new CinemaHallDto(free, occupied);
    }

    private void setSeatOccupation(Set<Seat> free, Set<Seat> occupied) {
        for (int i = 0; i < ROWS; i++) {
            boolean[] row = seats[i];
            for (int j = 0; j < SEATS; j++) {
                if (row[j])
                    occupied.add(new Seat(i + 1, j + 1));
                else
                    free.add(new Seat(i + 1, j + 1));
            }
        }
    }
}
