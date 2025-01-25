package org.wrk.date.holiday;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * <h3>ObservedHolidays</h3>class extends the FederalHolidays class and provides the ability to not observe any of the U.S. federal holidays.
 * <h4>Functionality</h4>
 * <li>holidayUnobserved - add a holiday to the unobserved set.
 * <li>isUnobserved - returns true if the holiday has been made unobserved else false.
 * <br/><br/>
 * <h4>Remove Holiday</h4>
 * <p>Remove a holiday from observance by specifying the name of the holiday from the list below to the holidayUnobserved method.</p>
 * <br/>
 * <table>
 * <caption><b>Holiday Names</b></caption>
 * <tr></tr>
 * <tr><td>CHRISTMAS_DAY</td></tr>
 * <tr><td>COLUMBUS_DAY</td></tr>
 * <tr><td>INDEPENDENCE_DAY</td></tr>
 * <tr><td>JUNETEENTH_DAY</td></tr>
 * <tr><td>LABOR_DAY</td></tr>
 * <tr><td>MARTINLUTHERKINGJR_DAY</td></tr>
 * <tr><td>MEMORIAL_DAY</td></tr>
 * <tr><td>NEWYEARS_DAY</td></tr>
 * <tr><td>PRESIDENTS_DAY</td></tr>
 * <tr><td>THANKSGIVING_DAY</td></tr>
 * <tr><td>VETERANS_DAY</td></tr>
 * </table>
 * <p></p>
 * @see org.wrk.date.holiday.FederalHolidays
 * @author Kelly Willard
 */
final public class ObservedHolidays extends FederalHolidays implements Cloneable {

	private Set<String> unobserved = new HashSet<>();
	
	/**
	 * <p>constructor</p>
	 */
	public ObservedHolidays() {
	}

	/**
	 * <p>constructor w/param</p>
	 */
	public ObservedHolidays(int year) {
		super(year);
	}

	/**
	 * <p>Clone the ObservedHolidays class.</p>
	 * @param iyear
	 * @return ObservedHolidays
	 */
	public ObservedHolidays clone(int iyear) {
		// Validate year value.  If invalid year, default to current year.
		iyear = 0 < iyear && iyear < MAXIMUM_YEAR ? iyear : this.getYear();
		
		ObservedHolidays response = new ObservedHolidays(iyear);
		
		// Clone the set of unobserved holiday names.
		response.setUnobserved(this.getUnobserved());
		
		// Clone the Saturday observable flag.
		response.setSaturdayObservable(this.isSaturdayObservable());
		
		// Clone the Sunday observable flag.
		response.setSundayObservable(this.isSundayObservable());
		
		// Generate the holiday list for the response object.
		response.init();
		
		return response;
	}
	
	/**
	 * <p>Get the set of unobserved holidays.</p>
	 * @return the unobserved set
	 */
	private Set<String> getUnobserved() {
		return unobserved;
	}
	
	/**
	 * <p>Add a holiday to the unobserved set.</p>
	 * 
	 * @param holidayName in HolidayEnum format.
	 * @return true if holiday is unobserved else false.
	 * @throws Exception if parameter is an invalid holiday name.
	 */
	public boolean holidayUnobserved(String holidayName) throws Exception {
		boolean response = false;
		
		if(holidayName != null && !holidayName.isEmpty()) {
			// Retrieve the holiday to be unobserved.
			HolidayEnum removeHoliday = HolidayEnum.valueOf(holidayName);
			
			// Add the holiday name to the set.
			response = this.getUnobserved().add(removeHoliday.name());
		}
		
		return response;
	}
		
	/**
	 * <p>Does the calendar date match a holiday?</p>
	 * <p></p>
	 * @param date
	 * @return true if holiday else false.
	 * @throws Exception if holiday list is empty or date is null.
	 */
	public boolean isHoliday(Calendar date) throws Exception {
		return this.whichHoliday(date) != null;
	}

	/**
	 * <p>Does the calendar date match a holiday?</p>
	 * @param date
	 * @return true if holiday else false.
	 * @throws Exception if holiday list is empty or date is null.
	 */
	public boolean isHoliday(Date date) throws Exception {
		return this.whichHoliday(date) != null;
	}
	
	/**
	 * <p>Is the holiday in the unobserved set?</p>
	 * @param holidayName
	 * @return true if holiday name is in the unobserved set else false.
	 */
	public boolean isUnobserved(String holidayName) {
		return this.getUnobserved().contains(holidayName);
	}

	/**
	 * @param unobserved the unobserved to set
	 */
	public void setUnobserved(Set<String> unobserved) {
		if(unobserved != null) {
			this.unobserved = unobserved;	
		}
	}
	
	/**
	 * <p>List all observed holidays, excluding the unobserved.</p>
	 * @return String[]
	 * @see org.wrk.date.holiday.FederalHolidays
	 */
	public String[] toHolidays() {
		// Get the holidays.
		String[] lines = super.toHolidays();
		
		//  Return value.
		String[] response = new String[lines.length - this.getUnobserved().size()];
		
		int index = 0;
		
		// Parse each line.
		for (String line : lines) {
			// Split up the current line.
			String[] eachLine = line.split(",");
			// Match the holiday enumeration to the unobserved list.
			if(!this.isUnobserved(eachLine[1])) {
				// If it doesn't match, add it to the array.
				response[index++] = line;
			}
		}
		
		return response;
	}

	/**
	 * <p>Which holiday does the calendar date match?</p>
	 * @param date
	 * @return Holiday if date matches the holiday date else null.
	 * @throws Exception if holiday list is empty or date is null.
	 */
	public Holiday whichHoliday(Calendar date) throws Exception {
		// Determine which holiday.
		Holiday holiday = super.whichHoliday(date);
		// Is the date a holiday?
		if(holiday != null) {
			// Is the holiday observed?
			if(this.isUnobserved(holiday.getDay().name())) {
				// The holiday is not observed.
				holiday = null;
			}			
		}
		
		return holiday;
	}
	
	/**
	 * <p>Which holiday does the calendar date match?</p>
	 * @param date
	 * @return Holiday if date matches the holiday date else null.
	 * @throws Exception if holiday list is empty or date is null.
	 */
	public Holiday whichHoliday(Date date) throws Exception {
		// Determine which holiday.
		Holiday holiday = super.whichHoliday(date);
		// Is the date a holiday?
		if(holiday != null) {
			// Is the holiday not observed?
			if(this.isUnobserved(holiday.getDay().name())) {
				// The holiday is not observed.
				holiday = null;
			}			
		}
		
		return holiday;
	}
}