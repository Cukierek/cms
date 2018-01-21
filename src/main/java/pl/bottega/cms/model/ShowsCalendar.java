package pl.bottega.cms.model;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

public class ShowsCalendar {

	private LocalDateTime fromDate, untilDate;
	private Set<String> weekDays;
	private Set<LocalTime> hours;

	public LocalDateTime getFromDate() {
		return fromDate;
	}

	public LocalDateTime getUntilDate() {
		return untilDate;
	}

	public Set<String> getWeekDays() {
		return weekDays;
	}

	public Set<LocalTime> getHours() {
		return hours;
	}

	public void setFromDate(LocalDateTime fromDate) {
		this.fromDate = fromDate;
	}

	public void setUntilDate(LocalDateTime untilDate) {
		this.untilDate = untilDate;
	}

	public void setWeekDays(Set<String> weekDays) {
		this.weekDays = weekDays;
	}

	public void setHours(Set<LocalTime> hours) {
		this.hours = hours;
	}
}
