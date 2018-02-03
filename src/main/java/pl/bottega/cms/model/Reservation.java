package pl.bottega.cms.model;

import pl.bottega.cms.model.commands.CreateReservationCommand;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Customer customer;

    @OneToMany
    private Set<Tickets> tickets;

    @OneToMany
    private Set<Seat> seats;

    private Long showId;

    public Reservation(CreateReservationCommand command) {
        this.showId = command.getShowId();
        this.customer = command.getCustomer();
        this.tickets = command.getTickets();
        this.seats = command.getSeats();
    }

}
