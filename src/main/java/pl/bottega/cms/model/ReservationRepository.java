package pl.bottega.cms.model;

import java.util.List;

public interface ReservationRepository {

    void save(Reservation reservation);

    List<Reservation> getReservations(Long showId);

}
