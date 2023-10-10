package org.ebbane.model;

import java.util.List;

public class MonthlyCalendar {
  private String calendarTime;
  private List<Day> days;

  public String getCalendarTime() {
    return calendarTime;
  }

  public void setCalendarTime(String calendarTime) {
    this.calendarTime = calendarTime;
  }

  public List<Day> getDays() {
    return days;
  }

  public void setDays(List<Day> days) {
    this.days = days;
  }
}
