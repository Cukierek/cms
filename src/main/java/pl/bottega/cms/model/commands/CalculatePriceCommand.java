package pl.bottega.cms.model.commands;

import pl.bottega.cms.model.Ticket;

import java.util.Set;

public class CalculatePriceCommand implements Command {

	Long showId;
	Set<Ticket> tickets;

	public CalculatePriceCommand() {
	}

	public Long getShowId() {
		return showId;
	}

	public void setShowId(Long showId) {
		this.showId = showId;
	}

	public Set<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(Set<Ticket> tickets) {
		this.tickets = tickets;
	}

	public void validate(ValidationErrors errors) {
		validatePresence(errors, "showId", showId);
		validatePresence(errors, "tickets", tickets);
	}
}
