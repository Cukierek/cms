package pl.bottega.cms.model;

public interface ReservationRepository {

    void save(Reservation reservation);

    Reservation get(Long reservationId);

}
