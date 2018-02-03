package pl.bottega.cms.model.commands;

import pl.bottega.cms.model.Customer;
import pl.bottega.cms.model.Seat;
import pl.bottega.cms.model.Tickets;

import java.util.Set;

public class CreateReservationCommand implements Command {

    Long showId;
    Customer customer;
    Set<Tickets> tickets;
    Set<Seat> seats;

    public Long getShowId() {
        return showId;
    }

    public void setShowId(Long showId) {
        this.showId = showId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<Tickets> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Tickets> tickets) {
        this.tickets = tickets;
    }

    public Set<Seat> getSeats() {
        return seats;
    }

    public void setSeats(Set<Seat> seats) {
        this.seats = seats;
    }
}
