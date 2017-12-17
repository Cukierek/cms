package pl.bottega.cms.domain;

import pl.bottega.cms.domain.commands.CreateCinemaCommand;

import javax.persistence.*;

@Entity
@Table(name = "cinemas")
public class Cinema {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String city;

    public Cinema() {
    }

    public Cinema(CreateCinemaCommand cmd) {
        this.name = cmd.getName();
        this.city = cmd.getCity();
    }
}
