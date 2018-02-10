package pl.bottega.cms.model;

import javax.persistence.Embeddable;
import javax.persistence.Table;

@Embeddable
@Table(name = "seats")
public class Seat {

    private Integer row;
    private Integer seatNumber;

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public Integer getRow() {

        return row;
    }

}
