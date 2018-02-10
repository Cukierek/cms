package pl.bottega.cms.model;

import javax.persistence.Embeddable;
import javax.persistence.Table;

@Embeddable
@Table(name = "seats")
public class Seat {

    private Integer row;
    private Integer seat;

    public Integer getSeatNumber() {
        return seat;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
    }
}
