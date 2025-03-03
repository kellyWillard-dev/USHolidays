package org.wrk.date.holiday;

import java.util.Calendar;

/**
 * <h1>Holiday</1>class provides information of each federal holiday.
 * <hr>
 * <h3>Elements</3>
 * <li>date is the calendar date when the holiday occurs.
 * <li>day is the name of the holiday.
 * <li>observed flag indicates where the day is observed (true) or occurs on the actual day (false).
 * <br/><br/>
 * @author Kelly Willard
 */
public class Holiday {

	private Calendar date;
	
	private HolidayEnum day;
	
	private boolean observed = true;
	
	/**
	 * constructor
	 */
	public Holiday() {
	}

	/**
	 * @return Calendar date of holiday
	 */
	public Calendar getDate() {
		return date;
	}

	/**
	 * @return HolidayEnum day name of holiday
	 */
	public HolidayEnum getDay() {
		return day;
	}

	/**
	 * @return boolean observed flag true if observed else false if actual day.
	 */
	public boolean isObserved() {
		return observed;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Calendar date) {
		this.date = date;
	}

	/**
	 * @param day the day to set
	 */
	public void setDay(HolidayEnum day) {
		this.day = day;
	}

	/**
	 * @param observed the observed to set
	 */
	public void setObserved(boolean observed) {
		this.observed = observed;
	}
}