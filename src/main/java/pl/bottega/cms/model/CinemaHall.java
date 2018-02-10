package pl.bottega.cms.model;


import pl.bottega.cms.model.commands.CreateReservationCommand;

import java.util.List;
import java.util.Set;


public class CinemaHall {

    private final Integer ROWS = 15;
    private final Integer SEATS = 10;

    boolean[][] seats = new boolean[ROWS][SEATS];

    public CinemaHall(List<Reservation> currentReservations) {
        currentReservations.forEach(this::getSeatsFromReservation);
    }

    void getSeatsFromReservation(Reservation reservation) {
        reservation.getSeats().forEach(this::reserveSeat);
    }


    void reserveSeat(Seat seat) {
        seats[seat.getRow() - 1][seat.getSeatNumber() - 1] = true;

    }


    public boolean checkReservations(CreateReservationCommand cmd) {

        return false;
    }


    public boolean checkSeatAvailability(Seat seat) {
        return (!(seat.getRow() > ROWS || seat.getSeatNumber() > SEATS || seats[seat.getRow() - 1][seat.getSeatNumber() - 1]));
    }
}
