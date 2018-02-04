package pl.bottega.cms.model;

import javax.persistence.*;

@Embeddable
@Table(name = "tickets")
public class Ticket {

    String kind;
    int count;



}
