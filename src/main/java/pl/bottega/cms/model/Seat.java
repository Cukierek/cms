package pl.bottega.cms.model;

import javax.persistence.*;

@Entity
@Table(name = "seats")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer row;
    private Integer seatNumber;

}
