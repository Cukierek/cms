package pl.bottega.cms.application;

import pl.bottega.cms.model.ReservationQuery;

public interface ReservationFinder {

    Object search(ReservationQuery reservationQuery);


}
