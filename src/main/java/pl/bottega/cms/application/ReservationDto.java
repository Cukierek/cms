package pl.bottega.cms.application;

import pl.bottega.cms.model.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public class ReservationDto {
    Long id;
    ReservedShowDto show;
    ReservedMovieDto movie;
    Set<Seat> seats;
    Set<Ticket> tickets;
    Customer customer;
    ReservationStatus status;
    BigDecimal totalPrice;



    public ReservationDto(){}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public class ReservedShowDto  {
        private Long id;
        private LocalDateTime time;


        public ReservedShowDto(Long id, LocalDateTime time) {
            this.id = id;
            this.time = time;
        }
    }


    public class ReservedMovieDto{

        private Long id;
        private String title;


        public ReservedMovieDto(Long id, String title) {
            this.id = id;
            this.title = title;
        }
    }

}
