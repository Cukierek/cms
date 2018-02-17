package pl.bottega.cms.model;


import java.util.List;

public interface ReservationRepository extends Repository<Reservation> {

    List<Reservation> getReservations(Long showId);

}
