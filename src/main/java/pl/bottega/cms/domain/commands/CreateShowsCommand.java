package pl.bottega.cms.domain.commands;

import pl.bottega.cms.domain.ShowsCalendar;

import java.time.LocalDateTime;
import java.util.Set;

public class CreateShowsCommand implements Command {

	private Long movieId;
	private Long cinemaId;
	private Set<LocalDateTime> dates;
	private ShowsCalendar showsCalendar;

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

	public ShowsCalendar getShowsCalendar() {
		return showsCalendar;
	}

	public void setShowsCalendar(ShowsCalendar showsCalendar) {
		this.showsCalendar = showsCalendar;
	}

	public boolean isShowsCalendarPresent() {
		return showsCalendar != null;
	}

	public boolean isHasDates() {
		return !dates.isEmpty() && dates != null;
	}

	public boolean isHasBoth() {
		return isShowsCalendarPresent() && isHasDates();
	}

	public void validate(ValidationErrors errors) {
		validatePresence(errors, "movieId", movieId);
		validatePresence(errors, "cinemaId", cinemaId);
		if(isShowsCalendarPresent()) validatePresence(errors, "showsCalendar", showsCalendar);
		if(isHasDates()) validatePresence(errors, "dates", dates);
		boolean requestHasTooManyParameters = isHasBoth();
		validateRequestFormat(errors, requestHasTooManyParameters, "dates or calendar");
	}
}
