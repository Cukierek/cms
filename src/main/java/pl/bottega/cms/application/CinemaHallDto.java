package pl.bottega.cms.application;

import pl.bottega.cms.model.Seat;

import java.util.Set;

public class CinemaHallDto {

    private Set<Seat> free;
    private Set<Seat> occupied;

    public CinemaHallDto(Set<Seat> free, Set<Seat> occupied) {
        this.free = free;
        this.occupied = occupied;
    }

    public Set<Seat> getFree() {
        return free;
    }

    public void setFree(Set<Seat> free) {
        this.free = free;
    }

    public Set<Seat> getOccupied() {
        return occupied;
    }

    public void setOccupied(Set<Seat> occupied) {
        this.occupied = occupied;
    }
}
