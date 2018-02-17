package pl.bottega.cms.model.commands;

import pl.bottega.cms.model.Customer;
import pl.bottega.cms.model.Seat;
import pl.bottega.cms.model.ShowRepository;
import pl.bottega.cms.model.Ticket;

import java.util.HashSet;
import java.util.Set;


public class CreateReservationCommand implements Command {

    private Long showId;
    private Set<Ticket> tickets;
    private Set<Seat> seats;
    private Customer customer;


    public CreateReservationCommand(){}

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

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {

        this.tickets = tickets;
    }

    public Set<Seat> getSeats() {
        return seats;
    }

    public void setSeats(Set<Seat> seats) {

        this.seats = seats;
    }

    public void validate(ValidationErrors errors) {
        validatePresence(errors, "showId", showId);
        validatePresence(errors, "tickets", tickets);
        validatePresence(errors, "seats", seats);
        validatePresence(errors, "phone", customer.getPhone());
        validatePresence(errors, "firsName", customer.getFirstName());
        validatePresence(errors, "lastName", customer.getLastName());
        validatePresence(errors, "email", customer.getEmail());
        validateFormat(errors, "email", customer.getEmail(), "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$");



    }









}

