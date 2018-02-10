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
}
