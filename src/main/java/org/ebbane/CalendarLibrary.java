package org.ebbane;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.ebbane.model.Day;
import org.ebbane.model.MonthlyCalendar;

public class CalendarLibrary {

  private static final String MONTH_YEAR_PATTERN = "MMMM yyyy";

  /**
   * private class constructor.
   */
  private CalendarLibrary() {
  }

  /**
   * method for generating a String containing the html tags used to display a calendar according to
   * the parameters supplied.
   *
   * @param year  the desired year for the calendar
   * @param month the desired month for the calendar
   * @return String with calendar visual
   * @author Ebbane DIET
   */
  public static String generateHTMLCalendar(Integer year, Integer month) {
    Calendar calendar = getCalendarInstance(year, month);
    String monthYear = formatCalendarTime(calendar.getTime());
    return buildHTML(month, monthYear, calendar);
  }

  /**
   * method for generating an object containing the information needed to process a calendar by
   * month.
   *
   * @param year  the desired year for the calendar
   * @param month the desired month for the calendar
   * @return MonthlyCalendar object that contain list of days in the selected month
   * @author Ebbane DIET
   */
  public static MonthlyCalendar generateCalendar(Integer year, Integer month) {
    Calendar calendar = getCalendarInstance(year, month);
    MonthlyCalendar monthlyCalendar = new MonthlyCalendar();
    monthlyCalendar.setCalendarTime(formatCalendarTime(calendar.getTime()));

    List<Day> days = new ArrayList<>();

    int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

    for (int dayOfMonth = 1; dayOfMonth <= daysInMonth; dayOfMonth++) {
      Day day = createDayOfTheMonth(calendar);
      days.add(day);
      calendar.add(Calendar.DAY_OF_MONTH, 1);
    }
    monthlyCalendar.setDays(days);
    return monthlyCalendar;
  }

  private static String buildHTML(Integer month, String monthYear, Calendar calendar) {
    StringBuilder htmlBuilder = new StringBuilder();
    buildCalendarHeader(monthYear, htmlBuilder);
    calendar.set(Calendar.DAY_OF_MONTH, 1);
    int firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
    htmlBuilder.append("<td></td>".repeat(Math.max(0, firstDayOfWeek - 1)));
    fillCalendarCells(month, calendar, htmlBuilder);
    manageRemainingEmptyCells(calendar, htmlBuilder);
    buildCalendarFooter(htmlBuilder);
    htmlBuilder.append("</html>");
    return htmlBuilder.toString();
  }

  private static void buildCalendarHeader(String monthYear, StringBuilder htmlBuilder) {
    htmlBuilder.append("<html>");
    htmlBuilder.append("<head>");
    htmlBuilder.append("<title>Calendar</title>");
    htmlBuilder.append("</head>");
    htmlBuilder.append("<body>");
    htmlBuilder.append("<h2>").append(monthYear).append("</h2>");
    htmlBuilder.append("<table border=\"1\">");
    htmlBuilder.append(
        "<tr><th>Sun</th><th>Mon</th><th>Tue</th><th>Wed</th><th>Thu</th><th>Fri</th><th>Sat</th></tr>");
  }

  private static void buildCalendarFooter(StringBuilder htmlBuilder) {
    htmlBuilder.append("</tr>");
    htmlBuilder.append("</table>");
    htmlBuilder.append("</body>");
    htmlBuilder.append("</html>");
  }

  private static void fillCalendarCells(Integer month, Calendar calendar,
      StringBuilder htmlBuilder) {
    for (; calendar.get(Calendar.MONTH) == (month - 1); calendar.add(Calendar.DAY_OF_MONTH, 1)) {
      int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
      htmlBuilder.append("<td>").append(dayOfMonth).append("</td>");
      if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
        htmlBuilder.append("</tr><tr>");
      }
    }
  }

  private static void manageRemainingEmptyCells(Calendar calendar, StringBuilder htmlBuilder) {
    while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
      htmlBuilder.append("<td></td>");
      calendar.add(Calendar.DAY_OF_MONTH, 1);
    }
  }


  private static Day createDayOfTheMonth(Calendar calendar) {
    Day day = new Day();
    day.setDayOfMonth(calendar.get(Calendar.DAY_OF_MONTH));
    day.setDayOfWeek(calendar.get(Calendar.DAY_OF_WEEK));
    return day;
  }

  /**
   * Initialize a calendar (java.util.Calendar) with the information provided
   */
  private static Calendar getCalendarInstance(int year, int month) {
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.YEAR, year);
    calendar.set(Calendar.MONTH, month - 1);
    return calendar;
  }

  /**
   * Change calendar date format
   *
   * @return String "MMMM yyyy". ex "novembre 2023"
   */
  private static String formatCalendarTime(Date calendarDate) {
    SimpleDateFormat dateFormat = new SimpleDateFormat(MONTH_YEAR_PATTERN);
    return dateFormat.format(calendarDate);
  }

}