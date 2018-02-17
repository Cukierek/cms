package pl.bottega.cms.infrastructure;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.bottega.cms.application.ReservationDto;
import pl.bottega.cms.application.ReservationFinder;
import pl.bottega.cms.model.ReservationQuery;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;


@Component
public class JpaReservationFinder implements ReservationFinder {


    private EntityManager entityManager;

    @Override
    @Transactional
    public List<ReservationDto> search(ReservationQuery reservationQuery) {

        return entityManager.createQuery("select r from Reservation r " +
                "where r.customer.lastName like :customerName " +
                "and r.status = :status and r.showId IN (select s.id from Show s where s.date > :date)")
                .setParameter("customerName", reservationQuery.getQuery())
                .setParameter("status", reservationQuery.getStatus())
                .setParameter("date", new Date())
                .getResultList();
    }



}
