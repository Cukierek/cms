package pl.bottega.cms.Utils;

import jdk.nashorn.internal.parser.JSONParser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateUtils {

	public static LocalDateTime convertToLocalDateTime(String initialDateString) {
		String[] dateAndTime = initialDateString.split(" ");
		String date = dateAndTime[0];
		String time = dateAndTime[1];
		LocalDate localDate = convertToLocalDate(date);
		LocalTime localTime = convertToLocalTime(time);
		return LocalDateTime.of(localDate, localTime);
	}

	public static LocalDate convertToLocalDate(String dateString) {
		String[] dateIngredients = dateString.split("/");
		String year = dateIngredients[0], month = dateIngredients[1], dayOfMonth = dateIngredients[2];
		int convertedYear = Integer.parseInt(year), convertedMonth = Integer.parseInt(month),
				convertedDayOfMonth = Integer.parseInt(dayOfMonth);
		return LocalDate.of(convertedYear, convertedMonth, convertedDayOfMonth);
	}

	public static LocalTime convertToLocalTime(String timeString) {
		String[] timeIngredients = timeString.split(":");
		String hour = timeIngredients[0], minute = timeIngredients[1];
		int convertedHour = Integer.parseInt(hour), convertedMinute = Integer.parseInt(minute);
		return LocalTime.of(convertedHour, convertedMinute);
	}
}
