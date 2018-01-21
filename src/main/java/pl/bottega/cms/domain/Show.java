package pl.bottega.cms.domain;

import pl.bottega.cms.domain.commands.CreateShowsCommand;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="shows")
public class Show {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name="cinema_id")
	private Cinema cinema;

	@ManyToOne
	@JoinColumn(name="movie_id")
	private Movie movie;

	private LocalDateTime date;

	public Show() {
	}

	public Show(Cinema cinema, Movie movie, LocalDateTime date) {
		this.cinema = cinema;
		this.movie = movie;
		this.date = date;
	}
}
