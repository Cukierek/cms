package pl.bottega.cms.application;

import java.math.BigDecimal;
import java.util.List;

public class PriceDto {
	private List<TicketDto> tickets;
	private BigDecimal totalPrice;

	public List<TicketDto> getTickets() {
		return tickets;
	}

	public void setTickets(List<TicketDto> tickets) {
		this.tickets = tickets;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
}
