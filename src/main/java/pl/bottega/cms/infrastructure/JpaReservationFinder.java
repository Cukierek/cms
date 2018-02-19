package pl.bottega.cms.infrastructure;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.bottega.cms.application.ReservationDto;
import pl.bottega.cms.application.ReservationFinder;
import pl.bottega.cms.application.ShowDto;
import pl.bottega.cms.model.Reservation;
import pl.bottega.cms.model.ReservationQuery;
import pl.bottega.cms.model.ReservationStatus;
import pl.bottega.cms.model.Show;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;


@Component
public class JpaReservationFinder implements ReservationFinder {


    private EntityManager entityManager;

    public JpaReservationFinder(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public List<ReservationDto> search(ReservationQuery reservationQuery) {

        List<ReservationDto> results = new LinkedList<>();

        List<Reservation> reservations =  entityManager.createQuery("select r from Reservation r, Show s " +
                "where s.id = r.showId " +
                "and s.date > :date " +
                "and r.customer.lastName like :customerName " +
                "and r.status = :status ")
                .setParameter("customerName", "%" + reservationQuery.getQuery() + "%")
                .setParameter("status", ReservationStatus.PENDING)
                .setParameter("date", LocalDateTime.now())
                .getResultList();

        for(Reservation r : reservations){
            Show show = (Show) entityManager.createQuery("select s from Show s " +
                    "where s.id = :id ")
                    .setParameter("id", r.getShowId())
                    .getSingleResult();
            results.add(new ReservationDto(r, show));
        }

        return results;
    }


}
