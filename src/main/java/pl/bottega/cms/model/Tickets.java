package pl.bottega.cms.model;

import javax.persistence.*;

@Entity
@Table(name = "tickets")
public class Tickets {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String kind;
    int count;

}
