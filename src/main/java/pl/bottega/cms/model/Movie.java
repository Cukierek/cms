package pl.bottega.cms.model;

import pl.bottega.cms.model.commands.CreateMovieCommand;
import pl.bottega.cms.model.commands.SetTicketPricesCommand;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "movies")
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String title;

	private String description;

	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> actors;

	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> genres;

	private Integer minAge;

	private Integer length;

	@Embedded
	private TicketPrices ticketPrices;


	public Movie() {
	}

	public Movie(CreateMovieCommand cmc) {
		this.actors = cmc.getActors();
		this.description = cmc.getDescription();
		this.genres = cmc.getGenres();
		this.length = cmc.getLength();
		this.minAge = cmc.getMinAge();
		this.title = cmc.getTitle();
	}


	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public Set<String> getActors() {
		return actors;
	}

	public Set<String> getGenres() {
		return genres;
	}

	public TicketPrices getTicketPrices() {
		return ticketPrices;
	}

	public Integer getMinAge() {
		return minAge;
	}

	public Integer getLength() {
		return length;
	}

	public void setPrices(TicketPrices ticketPrices){
		this.ticketPrices = ticketPrices;
			}


}
