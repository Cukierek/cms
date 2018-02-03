package pl.bottega.cms.model;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

public class ShowsCalendar {

	private String fromDate, untilDate;
	private Set<String> weekDays;
	private Set<String> hours;

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getUntilDate() {
		return untilDate;
	}

	public void setUntilDate(String untilDate) {
		this.untilDate = untilDate;
	}

	public Set<String> getWeekDays() {
		return weekDays;
	}

	public void setWeekDays(Set<String> weekDays) {
		this.weekDays = weekDays;
	}

	public Set<String> getHours() {
		return hours;
	}

	public void setHours(Set<String> hours) {
		this.hours = hours;
	}
}
