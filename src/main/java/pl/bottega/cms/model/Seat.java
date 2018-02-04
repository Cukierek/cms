package pl.bottega.cms.model;

import javax.persistence.*;

@Embeddable
@Table(name = "seats")
public class Seat {

    private Integer row;
    private Integer seatNumber;


}
