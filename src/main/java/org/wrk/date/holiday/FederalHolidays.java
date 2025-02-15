package org.wrk.date.holiday;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Stream;

/**
 * <h1>FederalHolidays</h1>class extends USHoliday and provides U.S. federal holiday functionality.
 * <h4>Functionality</h4>
 * <ul>
 * <li>isHoliday - will return true if the specified date is a holiday else false.
 * <li>toHolidays - provides a String array of holidays observed during the year.
 * <li>whichHoliday - will return the Holiday object if the specified date matches the holiday else null.<br/>
 * </ul>
 * <h4>Observable values</h4>
 * <p>There are 5 holidays that may occur during a weekend and are adjusted to be observed either on the prior Friday or
 * the following Monday.<br/></p>
 * <ul>
 * <li>saturdayObservable if set true indicates a holiday occurring on a Saturday will be observed on the prior Friday. 
 * <li>sundayObservable if set true indicates a holiday occurring on a Sunday will be observed on the following Monday.
 * </ul>
 * <br/><br/>
 * <p>The Observable values <b><i>default to true</i></b>.  If set false, the holiday is not observed.</p><br/><br/>
 * @see org.wrk.date.holiday.Holiday
 * @see org.wrk.date.holiday.HolidayEnum
 * @see org.wrk.date.holiday.HolidayRules
 * @see org.wrk.date.holiday.Holidays
 * @see org.wrk.date.holiday.USHoliday
 * 
 * @author Kelly Willard
 */
public class FederalHolidays extends USHoliday implements Holidays, HolidayRules {
	private SortedSet<Holiday> holidaySet = new TreeSet<>(Comparator.comparing(Holiday::getDay));
	
	private boolean saturdayObservable = true;
	
	private boolean sundayObservable = true;
	
	/**
	 * <p>constructor</p>
	 */
	public FederalHolidays() {
	}

	/**
	 * <p>constructor</p>
	 * @param year specified as an int value greater than 0 and less than MAXIMUM_YEAR.
	 */
	public FederalHolidays(int year) {
		super(year);
	}
	
	/**
	 * <p>Create a holiday object from the calendar date and the holiday enumeration.</p>
	 * <p>Because there are 5 actual holidays (meaning they can occur on any day of the week)<br/>
	 * the observed flag is set to false, indicating actual day.</p>
	 * <p>Observable holidays indicate whether or not to observe a holiday on another day if it occurs on a weekend.<br/>
	 * Traditionally, Saturday holidays observe on the prior Friday and Sunday holidays on the following Monday.</p>
	 * @param date value for the created Holiday.
	 * @param day HolidayEnum value for the created Holiday.
	 * @return Holiday if valid parameters exist else null.
	 */
	private Holiday createHoliday(Calendar date, HolidayEnum day) {
		Holiday holiday = null;
		
		if(date != null && day != null && this.isEnumeratedHoliday(day)) {
			holiday = new Holiday();
			// If the date occurs on a weekend, it's an actual holiday.						
			if(this.isWeekEnd(date)) {
				if(this.isObservableOnSaturday(date)) {
					// Observe the actual holiday on Friday.
					date.add(Calendar.DAY_OF_MONTH, -1);
				}
				else if(this.isObservableOnSunday(date)) {
					// Observe the actual holiday on Monday.
					date.add(Calendar.DAY_OF_MONTH, 1);
				}
				else if(isActualDay(day)) {
					// Flag date as an actual holiday.
					holiday.setObserved(false);
				}
			}
			else if(isActualDay(day)) {
				holiday.setObserved(false);
			}
			
			holiday.setDate(date);
			holiday.setDay(day);
		}
		
		return holiday;
	}

	/**
	 * @return holidaySet as a SortedSet.
	 */
	private SortedSet<Holiday> getHolidaySet() {
		return holidaySet;
	}
	
	/**
	 * <p>Initialize the FederalHolidays class.</p>
	 * <ul>
	 * <li>Calls the loadHolidays method.
	 * <li>Call this method after instantiating this class.
	 * <li>Call this method within an init-method setting for dependency injection.
	 * </ul>
	 */
	public void init() {
		this.loadHolidays();
	}
	
	/**
	 * <p>Does the calendar date match a holiday?</p>
	 * @param date to determine if holiday.
	 * @return true if holiday else false.
	 * @throws Exception if holiday list is empty or date is null.
	 */
	public boolean isHoliday(Calendar date) throws Exception {
		return this.whichHoliday(date) != null;
	}

	/**
	 * <p>Does the calendar date match a holiday?</p>
	 * @param date to determine if holiday.
	 * @return true if holiday else false.
	 * @throws Exception if holiday list is empty or date is null.
	 */
	public boolean isHoliday(Date date) throws Exception {
		return this.whichHoliday(date) != null;
	}

	/**
	 * <p>Are holidays occurring on a Saturday observable?</p>
	 */
	@Override
	public boolean isSaturdayObservable() {
		return saturdayObservable;
	}

	/**
	 * <p>Are holidays occurring on a Sunday observable?</p>
	 */
	@Override
	public boolean isSundayObservable() {
		return sundayObservable;
	}

	/**
	 * <p>Calculate all federal holidays for the year and load them into the holiday set.</p>
	 */
	private void loadHolidays() {
		Stream.of(HolidayEnum.values()).forEach(it -> {
			Holiday holiday = null;
			switch (it) {
			case CHRISTMAS_DAY:
				holiday = this.createHoliday(this.getChristmasDay(), HolidayEnum.CHRISTMAS_DAY);
				break;
			case COLUMBUS_DAY:
				holiday = this.createHoliday(this.getColumbusDay(), HolidayEnum.COLUMBUS_DAY);
				break;
			case INDEPENDENCE_DAY:
				holiday = this.createHoliday(this.getIndependenceDay(), HolidayEnum.INDEPENDENCE_DAY);
				break;
			case JUNETEENTH_DAY:
				holiday = this.createHoliday(this.getJuneteenthDay(), HolidayEnum.JUNETEENTH_DAY);
				break;
			case LABOR_DAY:
				holiday = this.createHoliday(this.getLaborDay(), HolidayEnum.LABOR_DAY);
				break;
			case MARTINLUTHERKINGJR_DAY:
				holiday = this.createHoliday(this.getMartinLutherKingJrDay(), HolidayEnum.MARTINLUTHERKINGJR_DAY);
				break;
			case MEMORIAL_DAY:
				holiday = this.createHoliday(this.getMemorialDay(), HolidayEnum.MEMORIAL_DAY);
				break;
			case NEWYEARS_DAY:
				holiday = this.createHoliday(this.getNewYearsDay(), HolidayEnum.NEWYEARS_DAY);
				break;
			case NEWYEARS_EVE:
				holiday = this.newYearsEveHoliday();
				break;
			case PRESIDENTS_DAY:
				holiday = this.createHoliday(this.getPresidentsDay(), HolidayEnum.PRESIDENTS_DAY);
				break;
			case THANKSGIVING_DAY:
				holiday = this.createHoliday(this.getThanksgivingDay(), HolidayEnum.THANKSGIVING_DAY);
				break;
			case VETERANS_DAY:
				holiday = this.createHoliday(this.getVeteransDay(), HolidayEnum.VETERANS_DAY);
				break;
			default:
				break;
			}
			
			// If the holiday isn't null, add it to the holiday set.
			if(holiday != null) {
				this.getHolidaySet().add(holiday);
			}
		});
	}
	
	/**
	 * <p>Anticipate the possibility that New Years Eve may be a holiday in the coming year.</p>
	 * @return Holiday if New Years Eve is observed as a holiday date else null.
	 */
	private Holiday newYearsEveHoliday() {
		// Create the next year value.
		int nextYear = this.getYear() + 1;
		
		// Instantiate the USHoliday object with next year value.
		USHoliday usHoliday = new USHoliday(nextYear);
		
		// Create the next new years day holiday.
		Holiday newYearsEve = this.createHoliday(usHoliday.getNewYearsDay(), HolidayEnum.NEWYEARS_EVE);
		
		// If New Years Eve, return holiday else null.
		return this.isNewYearsEve(newYearsEve) ? newYearsEve : null;
	}

	/**
	 * <p>Set the Saturday observable flag.</p>
	 * @param saturdayObservable the saturdayObservable to set
	 */
	@Override
	public void setSaturdayObservable(boolean saturdayObservable) {
		this.saturdayObservable = saturdayObservable;
	}

	/**
	 * <p>Set the Sunday observable flag.</p>
	 * @param sundayObservable the sundayObservable to set
	 */
	@Override
	public void setSundayObservable(boolean sundayObservable) {
		this.sundayObservable = sundayObservable;
	}

	/**
	 * <p>Provide a string array all holidays sorted by date in ascending order.</p>
	 * @return String[] - format | Date: format "EEEEE MM-dd-yyyy" | Holiday Enum | Holiday Name | Observable flag
	 */
	public String[] toHolidays() {
		// Create a holiday set that will be sorted by date, not by day.
		SortedSet<Holiday> toSet = new TreeSet<>(Comparator.comparing(Holiday::getDate));
		
		// Add the holidays to the new tree set.
		this.getHolidaySet().forEach(it -> toSet.add(it));
		
		String[] result = new String[toSet.size()];
		
		SimpleDateFormat sdf = new SimpleDateFormat("EEEEE MM-dd-yyyy");

		// Iteration counter.
		int index = 0;
		
		for(Holiday holiday : toSet) {
			result[index++] = String.format("%s,%s,%s%s",sdf.format(holiday.getDate().getTime()), holiday.getDay().name(), holiday.getDay().getHolidayName(), (holiday.isObserved() ? ",observed" : ""));
		}
		
		return result;
	}

	/**
	 * <p>Which holiday does the calendar date match?</p>
	 * @param date to determine which holiday.
	 * @return Holiday if date matches the holiday date else null.
	 * @throws Exception if holiday list is empty or date is null.
	 */
	public Holiday whichHoliday(Calendar date) throws Exception {
		Optional<Holiday> holiday = Optional.empty();
		
		if(date != null && !this.getHolidaySet().isEmpty()) {
			// Remove the time stamp from the calendar date.
			Calendar now = this.deleteTimestamp(date);
			
			// Does the date match a holiday?
			holiday = this.getHolidaySet().stream().filter(h -> h.getDate().compareTo(now) == 0).findFirst();
		}
		else {			
			if(this.getHolidaySet().isEmpty()) {
				throw new Exception("Holiday list is empty.  Invoke init() method after instantiating the FederalHolidays class.");
			}
			else if(date == null) {
				throw new Exception("Date is null. Cannot determine holiday.");
			}
		}
		
		return holiday.isPresent() ? holiday.get() : null;
	}
	
	/**
	 * <p>Which holiday does the calendar date match?</p>
	 * @param date to determine which holiday.
	 * @return Holiday if date matches the holiday date else null.
	 * @throws Exception if holiday list is empty or date is null.
	 */
	public Holiday whichHoliday(Date date) throws Exception {
		// Generate a new Calendar object.
		Calendar now = Calendar.getInstance();
		
		if(date != null) {
			// Set the new Calendar object to the date parameter.
			now.setTime(date);			
		}
		else {
			now = null;
		}
		
		// Determine which holiday.
		return this.whichHoliday(now);
	}
}