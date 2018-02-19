package pl.bottega.cms.model;

public class ReservationQuery {

    String query;
    ReservationStatus status;

    public ReservationQuery(String query, ReservationStatus status) {
        this.query = query;
        this.status = status;
    }

    public String getQuery() {
        return query;
    }
    public ReservationStatus getStatus() {
        return status;
    }

}
