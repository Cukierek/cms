package pl.bottega.cms.model;

import java.util.Set;

public interface ReservationRepository {

    void save(Reservation reservation);

    Set<Reservation> getReservations(Long showId);

}
