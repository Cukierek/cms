package pl.bottega.cms.application;

import pl.bottega.cms.model.ReservationQuery;

import java.util.List;

public interface ReservationFinder {

    List<ReservationDto> search(ReservationQuery reservationQuery);


}
