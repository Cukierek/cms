package pl.bottega.cms.model;

import javax.persistence.*;

@Embeddable
@Table(name = "tickets")
public class Ticket {

    String kind;
    int count;


    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
