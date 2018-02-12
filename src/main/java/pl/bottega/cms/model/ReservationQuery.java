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

    public void setQuery(String query) {
        this.query = query;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }



}
