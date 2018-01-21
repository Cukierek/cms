package pl.bottega.cms.model;

import pl.bottega.cms.model.commands.CreateCinemaCommand;

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

	public Cinema(CreateCinemaCommand createCinemaCommand) {
		this.name = createCinemaCommand.getName();
		this.city = createCinemaCommand.getCity();
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Cinema{" +
				"id=" + id +
				", name='" + name + '\'' +
				", city='" + city + '\'' +
				'}';
	}
}
