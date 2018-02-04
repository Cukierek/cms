package pl.bottega.cms.model.commands;

import com.fasterxml.jackson.annotation.JsonFormat;
import pl.bottega.cms.model.ShowsCalendar;

import java.time.LocalDate;
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
		if (movieId != null) {
			validateMovieId(errors);
		}

		if (cinemaId != null) {
			validateCinemaId(errors);
		}

		if (dates != null) {
			validateDates(errors);
			hasDates = true;
		}

		if (hasDates == false) {

			validatePresence(errors, "calendar", calendar);
		}

		if (hasDates && calendar != null) validateRequestFormat(errors, false, "dates or calendar");
		if(errors.any())throw new CommandInvalidException(errors);
	}

	private void validateCinemaId(ValidationErrors errors) {
		validatePresence(errors, "cinemaId", cinemaId);
		if (cinemaId < 1) errors.add("cinemaId","Can't be less than 1");
	}

	private void validateMovieId(ValidationErrors errors) {
		validatePresence(errors, "movieId", movieId);
		if (movieId < 1) errors.add("movieId","Can't be less than 1");
	}

	private void validateDates(ValidationErrors errors) {
		validatePresence(errors, "dates", dates);
		if (dates.isEmpty()) errors.add("dates","Can't be empty");
		for(LocalDateTime date: dates) {
			if (date.isBefore(LocalDateTime.now())) errors.add("dates","Date can't be in the past");
		}
	}

	private void validateCalendar(ValidationErrors errors) {

	}
}
