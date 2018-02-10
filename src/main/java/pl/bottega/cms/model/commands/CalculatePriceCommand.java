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
		validateShowId(errors);
		validateTickets(errors);
	}

	private void validateShowId(ValidationErrors errors) {
		validatePresence(errors, "showId", showId);
	}

	private void validateTickets(ValidationErrors errors) {
		validatePresence(errors, "tickets", tickets);
		tickets.stream().forEach(ticket -> {
			if (ticket == null) {
				errors.add("ticket", "Can't be empty");
			} else {
				if (ticket.getKind() == null) {
					errors.add("kind", "Can't be empty");
				} else {
					if (ticket.getKind().isEmpty()) errors.add("kind", "Can't be empty");
				}
				if (ticket.getCount() == null) {
					errors.add("count", "Can't be empty");
				} else {
					if(ticket.getCount() < 1) errors.add("count", "Can't be less than 1");
				}
			}
		});
	}
}
