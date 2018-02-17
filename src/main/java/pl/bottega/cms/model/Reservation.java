package pl.bottega.cms.model;


import pl.bottega.cms.model.commands.CreateReservationCommand;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    private Customer customer;

    @ElementCollection
    private Set<Ticket> tickets;

    @ElementCollection
    private Set<Seat> seats;

    @Column(name = "show_id")
    private Long showId;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    @Column(name = "total_cost")
    private BigDecimal totalCost;


    public Reservation(CreateReservationCommand command) {
        this.showId = command.getShowId();
        this.customer = command.getCustomer();
        this.tickets = command.getTickets();
        this.seats = command.getSeats();
        this.status = ReservationStatus.PENDING;
    }


    public Reservation() {
    }

    public Long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public Set<Seat> getSeats() {
        return seats;
    }

    public Long getShowId() {
        return showId;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }


    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }
}
