package pl.bottega.cms.model.commands;

import pl.bottega.cms.model.ShowsCalendar;

import java.util.Set;

public class CreateShowsCommand implements Command {

	private Long movieId;
	private Long cinemaId;
	private Set<String> dates;
	private ShowsCalendar showsCalendar;

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

	public Set<String> getDates() {
		return dates;
	}

	public void setDates(Set<String> dates) {
		this.dates = dates;
	}

	public ShowsCalendar getShowsCalendar() {
		return showsCalendar;
	}

	public void setShowsCalendar(ShowsCalendar showsCalendar) {
		this.showsCalendar = showsCalendar;
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
			validatePresence(errors, "showsCalendar", showsCalendar);
			validateFormat(errors,"showsCalendar", showsCalendar.getFromDate(), "([12]\\d{3}/(0[1-9]|1[0-2])/(0[1-9]|[12]\\d|3[01]))");
			validateFormat(errors,"showsCalendar", showsCalendar.getFromDate(), "(\\d{4})\\/(\\d{2})\\/(\\d{2})\\s(\\d{2}):(\\d{2})");
			validateFormat(errors,"showsCalendar", showsCalendar.getUntilDate(), "([12]\\d{3}/(0[1-9]|1[0-2])/(0[1-9]|[12]\\d|3[01]))");
		}

		if (hasDates && showsCalendar != null) validateRequestFormat(errors, false, "dates or calendar");
		if(errors.any())throw new CommandInvalidException(errors);
	}
}
