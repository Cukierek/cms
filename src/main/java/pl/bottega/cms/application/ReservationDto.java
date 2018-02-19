package pl.bottega.cms.application;

import pl.bottega.cms.model.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public class ReservationDto {

    private Long number;
    private ReservedShowDto show;
    private ReservedMovieDto movie;
    private Set<Seat> seats;
    private Set<Ticket> tickets;
    private Customer customer;
    private ReservationStatus status;
    private BigDecimal totalPrice;


    public ReservationDto(Reservation reservation, Show show) {
        this.number = reservation.getId();
        this.show = new ReservedShowDto(show.getId(), show.getDate());
        this.movie = new ReservedMovieDto(show.getMovie().getId(), show.getMovie().getTitle());
        this.seats = reservation.getSeats();
        this.tickets = reservation.getTickets();
        this.customer = reservation.getCustomer();
        this.status = reservation.getStatus();
        this.totalPrice = reservation.getTotalCost();

    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public ReservedShowDto getShow() {
        return show;
    }

    public void setShow(ReservedShowDto show) {
        this.show = show;
    }

    public ReservedMovieDto getMovie() {
        return movie;
    }

    public void setMovie(ReservedMovieDto movie) {
        this.movie = movie;
    }

    public Set<Seat> getSeats() {
        return seats;
    }

    public void setSeats(Set<Seat> seats) {
        this.seats = seats;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }


    private class ReservedMovieDto {
        private Long id;

        public ReservedMovieDto(Long id, String title) {
            this.id = id;
            this.title = title;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        private String title;

    }

    private class ReservedShowDto {

        private Long id;
        private LocalDateTime date;

        public ReservedShowDto(Long id, LocalDateTime date) {
            this.id = id;
            this.date = date;

        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public LocalDateTime getDate() {
            return date;
        }

        public void setDate(LocalDateTime date) {
            this.date = date;
        }
    }
}

