package pl.bottega.cms.model.commands;

import com.fasterxml.jackson.annotation.JsonFormat;
import pl.bottega.cms.model.ShowsCalendar;

import java.time.LocalDateTime;
import java.util.Set;

public class CreateShowsCommand implements Command {

	private Long movieId, cinemaId;
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm")
	private Set<LocalDateTime> dates;
	private ShowsCalendar calendar;

	private boolean hasDates = false;

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

	public Long getCinemaId() {
		return cinemaId;
	}

	public void setCinemaId(Long cinemaId) {
		this.cinemaId = cinemaId;
	}

	public Set<LocalDateTime> getDates() {
		return dates;
	}

	public void setDates(Set<LocalDateTime> dates) {
		this.dates = dates;
	}

	public ShowsCalendar getCalendar() {
		return calendar;
	}

	public void setCalendar(ShowsCalendar calendar) {
		this.calendar = calendar;
	}

	public boolean hasDates() {
		return hasDates;
	}

	public void validate(ValidationErrors errors) {
		validatePresence(errors, "movieId", movieId);
		validatePresence(errors, "cinemaId", cinemaId);

		if (dates != null) {
			hasDates = true;
			validatePresence(errors, "dates", dates);
		}

		if (hasDates == false) {

			validatePresence(errors, "calendar", calendar);
//			validateFormat(errors,"calendar", calendar.getFromDate(), "([12]\\d{3}/(0[1-9]|1[0-2])/(0[1-9]|[12]\\d|3[01]))");
//			validateFormat(errors,"calendar", calendar.getFromDate(), "(\\d{4})\\/(\\d{2})\\/(\\d{2})\\s(\\d{2}):(\\d{2})");
//			validateFormat(errors,"calendar", calendar.getUntilDate(), "([12]\\d{3}/(0[1-9]|1[0-2])/(0[1-9]|[12]\\d|3[01]))");
		}

		if (hasDates && calendar != null) validateRequestFormat(errors, false, "dates or calendar");
		if(errors.any())throw new CommandInvalidException(errors);
	}
}
