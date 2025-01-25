package org.wrk.date.holiday;

import java.util.Calendar;
import java.util.Date;

/**
 * <h3>USHoliday</h3>class calculates all U.S. federal holidays for a calendar year.
 * <p>
 * <table style="width:50%;">
 * 	<tr>
 * 		<th>Holiday</th>
 * 		<th>Occurs</th>
 * 	</tr> 
 *	<tr>
 *		<td align="center">Christmas Day</td>
 *		<td align="center">December 25th</td>
 *	</tr>
 *	<tr>
 *		<td align="center">Columbus Day</td>
 *		<td align="center">2nd Monday in October</td>
 *	</tr>
 *	<tr>
 *		<td align="center">Independence Day</td>
 *		<td align="center">July 4th</td>
 *	</tr>
 *	<tr>
 *		<td align="center">Juneteeth Day</td>
 *		<td align="center">June 19th</td>
 *	</tr>
 *	<tr>
 *		<td align="center">Labor Day</td>
 *		<td align="center">1st Monday in September</td>
 *	</tr>
 *	<tr>
 *		<td align="center">Martin Luther King Jr Day</td>
 *		<td align="center">3rd Monday in January</td>
 *	</tr>
 *	<tr>
 *		<td align="center">Memorial Day</td>
 *		<td align="center">Last Monday in May</td>
 *	</tr>
 *	<tr>
 *		<td align="center">New Years Day</td>
 *		<td align="center">January 1st</td>
 *	</tr>
 *	<tr>
 *		<td align="center">Presidents Day</td>
 *		<td align="center">3rd Monday in February</td>
 *	</tr>
 *	<tr>
 *		<td align="center">Thanksgiving Day</td>
 *		<td align="center">4th Thursday in November</td>
 *	</tr>
 *	<tr>
 *		<td align="center">Veterans Day</td>
 *		<td align="center">November 11th</td>
 *	</tr>
 * </table>
 * </p>
 * @author Kelly Willard
 */
public class USHoliday {

	protected final int MAXIMUM_YEAR = Calendar.getInstance().getActualMaximum(Calendar.YEAR);
	
	private final int NEXT = 1;
	
	private final int ONE_WEEK = 7;
	
	private final int PREVIOUS = 0;
	
	private int year = 0;
	
	/**
	 * <p>constructor</p>
	 */
	public USHoliday() {
		this.setYear(0);
	}
	
	/**
	 * <p>constructor with parameter</p>
	 * 
	 * @param year
	 */
	public USHoliday(int year) {
		this.setYear(year);
	}
	
	/**
	 * <p>Calculate Christmas day for the year.</p>
	 * @return Calendar
	 */
	private Calendar calculateChristmasDay() {
		Calendar christmasDay = this.deleteTimestamp(Calendar.getInstance());
		
		christmasDay.set(this.getYear(), Calendar.DECEMBER, 25);
		
		return christmasDay;		
	}
	
	/**
	 * <p>Calculate Columbus day for the year.</p>
	 * @return Calendar
	 */
	private Calendar calculateColumbusDay() {		
		Calendar columbusDay = this.deleteTimestamp(Calendar.getInstance());
		
		// Set date at the beginning of October.
		columbusDay.set(this.getYear(), Calendar.OCTOBER, 1);
		
		// Find the next MONDAY from the set date.
		int addDays = this.calculateWhenDayOccurs(NEXT, Calendar.MONDAY, columbusDay.get(Calendar.DAY_OF_WEEK));
		
		// Columbus day is the 2nd Monday of October.
		columbusDay.add(Calendar.DAY_OF_MONTH, addDays + ONE_WEEK);		
		
		return columbusDay;
	}
	
	/**
	 * <p>Calculate Independence day for the year.</p>
	 * @return Calendar
	 */
	private Calendar calculateIndependenceDay() {
		Calendar independenceDay = this.deleteTimestamp(Calendar.getInstance());
		
		independenceDay.set(this.getYear(), Calendar.JULY, 4);
		
		return independenceDay;		
	}
	
	/**
	 * <p>Calculate Juneteenth day for the year.</p>
	 * @return Calendar
	 */
	private Calendar calculateJuneteenthDay() {
		Calendar juneteenthDay = this.deleteTimestamp(Calendar.getInstance());
		
		juneteenthDay.set(this.getYear(), Calendar.JUNE, 19);
		
		return juneteenthDay;		
	}
	
	/**
	 * <p>Calculate Labor day for the year.</p>
	 * @return Calendar
	 */
	private Calendar calculateLaborDay() {		
		Calendar laborDay = this.deleteTimestamp(Calendar.getInstance());
		
		// Set date at the beginning of September.
		laborDay.set(this.getYear(), Calendar.SEPTEMBER, 1);
		
		// Find the next MONDAY from the set date.
		int addDays = this.calculateWhenDayOccurs(NEXT, Calendar.MONDAY, laborDay.get(Calendar.DAY_OF_WEEK));
		
		// Labor day is the 1st Monday of September.
		laborDay.add(Calendar.DAY_OF_MONTH, addDays);		
		
		return laborDay;
	}

	/**
	 * <p>Calculate Martin Luther King Jr day for the year.</p>
	 * @return Calendar
	 */
	private Calendar calculateMartinLutherKingJrDay() {		
		Calendar martinLutherKingJrDay = this.deleteTimestamp(Calendar.getInstance());

		// Set date at the beginning of January.
		martinLutherKingJrDay.set(this.getYear(), Calendar.JANUARY, 1);
		
		// Find the next MONDAY from the set date.
		int addDays = this.calculateWhenDayOccurs(NEXT, Calendar.MONDAY, martinLutherKingJrDay.get(Calendar.DAY_OF_WEEK));
		
		// Martin Luther King Jr day is the 3rd Monday of January.
		martinLutherKingJrDay.add(Calendar.DAY_OF_MONTH, addDays + (2 * ONE_WEEK));		
		
		return martinLutherKingJrDay;
	}
	
	/**
	 * <p>Calculate Memorial day for the year.</p>
	 * @return Calendar
	 */
	private Calendar calculateMemorialDay() {		
		Calendar memorialDay = this.deleteTimestamp(Calendar.getInstance());
		
		// Set date at the end of May.
		memorialDay.set(this.getYear(), Calendar.MAY, 31);
		
		// Find the previous MONDAY from the set date.
		int subtractDays = this.calculateWhenDayOccurs(PREVIOUS, Calendar.MONDAY, memorialDay.get(Calendar.DAY_OF_WEEK));
		
		// Memorial day is the last MONDAY of May.
		memorialDay.add(Calendar.DAY_OF_MONTH, subtractDays);		
		
		return memorialDay;
	}
	
	/**
	 * <p>Calculate New Years day for the year.</p>
	 * @return Calendar
	 */
	private Calendar calculateNewYearsDay() {
		Calendar newYearsDay = this.deleteTimestamp(Calendar.getInstance());
		
		newYearsDay.set(this.getYear(), Calendar.JANUARY, 1);
		
		return newYearsDay;		
	}
	
	/**
	 * <p>Calculate Presidents day for the year.</p>
	 * @return Calendar
	 */
	private Calendar calculatePresidentsDay() {
		Calendar presidentsDay = this.deleteTimestamp(Calendar.getInstance());
		
		// Set date at the beginning of February.
		presidentsDay.set(this.getYear(), Calendar.FEBRUARY, 1);
		
		// Find the next MONDAY from the set date.
		int addDays = this.calculateWhenDayOccurs(NEXT, Calendar.MONDAY, presidentsDay.get(Calendar.DAY_OF_WEEK));
		
		// Presidents day is the 3rd Monday of February.
		presidentsDay.add(Calendar.DAY_OF_MONTH, addDays + (2 * ONE_WEEK));		
		
		return presidentsDay;
	}
	
	/**
	 * <p>Calculate Thanksgiving day for the year.</p>
	 * @return Calendar
	 */
	private Calendar calculateThanksgivingDay() {
		Calendar thanksgivingDay = this.deleteTimestamp(Calendar.getInstance());
		
		// Set date at the beginning of November.
		thanksgivingDay.set(this.getYear(), Calendar.NOVEMBER, 1);
		
		// Find the next THURSDAY from the set date.
		int addDays = this.calculateWhenDayOccurs(NEXT, Calendar.THURSDAY, thanksgivingDay.get(Calendar.DAY_OF_WEEK));
		
		// Thanksgiving day is the 4th Thursday of November. 
		thanksgivingDay.add(Calendar.DAY_OF_MONTH, addDays + (3 * ONE_WEEK));		
		
		return thanksgivingDay;
	}
	
	/**
	 * <p>Calculate Veterans day for the year.</p>
	 * @return Calendar
	 */
	private Calendar calculateVeteransDay() {
		Calendar veteransDay = this.deleteTimestamp(Calendar.getInstance());
		
		veteransDay.set(this.getYear(), Calendar.NOVEMBER, 11);
		
		return veteransDay;
	}
	
	/**
	 * <p>Determine the number of days from a desired day that occurs within a calendar week.</p>
	 * <p>Example:<br>
	 * If the desired (day) is MONDAY and the current day (offset) is TUESDAY,<br>
	 * the previous MONDAY is -1 days away.<br>
	 * the next MONDAY is +6 days away.
	 * </p>
	 * @param direction either PREVIOUS or NEXT occurrence of desired day.
	 * @param day is the desired day of the week.  Calendar.SUNDAY (1) through Calendar.SATURDAY (7)
	 * @param offset is the current day in the week.  Calendar.SUNDAY (1) through Calendar.SATURDAY (7)
	 * @return the number of days until the day occurs.<br/>
	 * <p>
	 * PREVIOUS - value range -6 to 0<br/>
	 * NEXT - value range 0 to 6
	 * </p>
	 */
	private int calculateWhenDayOccurs(int direction, int day, int offset) {	
		int ireturn = 0;
		
		if(direction == NEXT) {
			// Is the current day less than or equal to the desired day?
			ireturn = offset <= day ? day - offset : (Calendar.SATURDAY - offset) + day;
		}
		else {
			// Is the current day less than the desired day?
			ireturn = offset < day ? (day - Calendar.SATURDAY) -  offset : day - offset;
		}
		
		return ireturn;
	}
	
	/**
	 * <p>Remove the timestamp from the calendar date.</p> 
	 * @param date
	 * @return Calendar
	 */
	public Calendar deleteTimestamp(Calendar date) {
		if(date != null) {
			date.set(Calendar.HOUR, 0);
			
			date.set(Calendar.HOUR_OF_DAY, 0);
			
			date.set(Calendar.MINUTE, 0);
			
			date.set(Calendar.SECOND, 0);
			
			date.set(Calendar.MILLISECOND, 0);
		}
		
		return date;
	}
	
	/**
	 * <p>Remove the timestamp from the calendar date.</p>
	 * @param date
	 * @return Calendar
	 */
	public Calendar deleteTimestamp(Date date) {
		Calendar now = Calendar.getInstance();
		
		if(date != null) {
			now.setTime(date);			
		}
		
		return date != null ? this.deleteTimestamp(now) : null;
	}
	
	/**
	 * <p>Get Christmas day.</p>
	 * @return Calendar
	 */
	public Calendar getChristmasDay() {
		return this.calculateChristmasDay();
	}

	/**
	 * <p>Get Columbus day.</p>
	 * @return Calendar
	 */
	public Calendar getColumbusDay() {
		return this.calculateColumbusDay();
	}

	/**
	 * <p>Get Independence day.</p>
	 * @return Calendar
	 */
	public Calendar getIndependenceDay() {
		return this.calculateIndependenceDay();
	}

	/**
	 * <p>Get Juneteenth day.</p>
	 * @return Calendar
	 */
	public Calendar getJuneteenthDay() {
		return this.calculateJuneteenthDay();
	}

	/**
	 * <p>Get Labor day.</p>
	 * @return Calendar
	 */
	public Calendar getLaborDay() {
		return this.calculateLaborDay();
	}

	/**
	 * <p>Get Martin Luther King Jr day.</p>
	 * @return Calendar
	 */
	public Calendar getMartinLutherKingJrDay() {
		return this.calculateMartinLutherKingJrDay();
	}

	/**
	 * <p>Get Memorial day.</p>
	 * @return Calendar
	 */
	public Calendar getMemorialDay() {
		return this.calculateMemorialDay();
	}

	/**
	 * <p>Get New Years day.</p>
	 * @return Calendar
	 */
	public Calendar getNewYearsDay() {
		return this.calculateNewYearsDay();
	}

	/**
	 * <p>Get Presidents day.</p>
	 * @return Calendar
	 */
	public Calendar getPresidentsDay() {
		return this.calculatePresidentsDay();
	}

	/**
	 * <p>Get Thanksgiving day.</p>
	 * @return Calendar
	 */
	public Calendar getThanksgivingDay() {
		return this.calculateThanksgivingDay();
	}

	/**
	 * <p>Get Veterans day.</p>
	 * @return Calendar
	 */
	public Calendar getVeteransDay() {
		return this.calculateVeteransDay();
	}

	/**
	 * <p>Get the calendar year.</p>
	 * @return int
	 */
	public int getYear() {
		return year;
	}

	/**
	 * <p>Set the calendar year.</p>
	 * @param year the year to set
	 */
	protected void setYear(int year) {
		if(0 < year && year < MAXIMUM_YEAR) {
			this.year = year;
		}
		else {
			// Set year to the current calendar year.
			this.year = Calendar.getInstance().get(Calendar.YEAR);
		}
	}
}