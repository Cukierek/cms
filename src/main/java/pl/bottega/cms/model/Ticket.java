package pl.bottega.cms.model;

import javax.persistence.*;

@Embeddable
@Table(name = "tickets")
public class Ticket {

    String kind;
    Integer count;

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Ticket)) return false;

		Ticket ticket = (Ticket) o;

		if (kind != null ? !kind.equals(ticket.kind) : ticket.kind != null) return false;
		return count != null ? count.equals(ticket.count) : ticket.count == null;
	}

	@Override
	public int hashCode() {
		int result = kind != null ? kind.hashCode() : 0;
		result = 31 * result + (count != null ? count.hashCode() : 0);
		return result;
	}
}
