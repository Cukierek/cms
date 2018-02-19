package pl.bottega.cms.application;

import pl.bottega.cms.model.Show;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class ShowDto {

    private Long id;
    private LocalDateTime time;

    public ShowDto(){}
    public ShowDto(Show show) {
        this.id = show.getId();
        this.time = LocalDateTime.from(show.getDate().toLocalTime());
    }

    public ShowDto(Long showId, LocalDateTime showDate) {
        this.id = showId;
        this.time = showDate;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
