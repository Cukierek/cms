package pl.bottega.cms.model;


import javax.persistence.Embeddable;

@Embeddable
public class Seat {

    private int row;
    private int seat;

    public Seat(Integer row, Integer seat) {
        this.row = row;
        this.seat = seat;
    }

    public Seat() {
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }
}
