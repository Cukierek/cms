package pl.bottega.cms.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import pl.bottega.cms.application.Receipt;
import pl.bottega.cms.application.ReceiptLine;
import pl.bottega.cms.model.commands.CalculatePricesCommand;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "shows")
public class Show {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "cinema_id")
	private Cinema cinema;

	@ManyToOne
	@JoinColumn(name = "movie_id")
	private Movie movie;

	@JsonFormat(pattern = "yyyy/MM/dd HH:mm")
	private LocalDateTime date;

	public Show() {
	}

	public Show(Cinema cinema, Movie movie, LocalDateTime date) {
		this.cinema = cinema;
		this.movie = movie;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public Cinema getCinema() {
		return cinema;
	}

	public Movie getMovie() {
		return movie;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public Receipt calculate(CalculatePricesCommand cmd) {
		Receipt receipt = new Receipt();
		BigDecimal totalPrice = BigDecimal.ZERO;

		cmd.getTickets().stream().forEach(ticket -> {
			BigDecimal total = BigDecimal.ZERO;
			BigDecimal unitPrice = movie.getTicketPrices().getPrices().get(ticket.getKind());
			BigDecimal count = BigDecimal.valueOf(ticket.getCount());
			total.add(unitPrice.multiply(count));
			ReceiptLine receiptLine = new ReceiptLine(ticket.getKind(), ticket.getCount(), unitPrice, total);
			receipt.addReceiptLine(receiptLine);
			totalPrice.add(total);
		});

		receipt.setTotalPrice(totalPrice);

		return receipt;
	}
}
