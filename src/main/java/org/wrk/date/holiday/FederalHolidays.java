package org.wrk.date.holiday;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Stream;

/**
 * <h3>FederalHolidays</h3>class extends USHoliday and provides U.S. federal holiday functionality.
 * <h4>Functionality</h4>
 * <li>isHoliday - will return true if the specified date is a holiday else false.
 * <li>toHolidays - provides a String array of holidays observed during the year.
 * <li>whichHoliday - will return the Holiday object if the specified date matches the holiday else null.<br/>
 * <h4>Observable values</h4>
 * <p>There are 5 holidays that may occur during a weekend and are adjusted to be observed either on the prior Friday or
 * the following Monday.<br/>
 * <li>saturdayObservable if set true indicates a holiday occurring on a Saturday will be observed on the prior Friday. 
 * <li>sundayObservable if set true indicates a holiday occurring on a Sunday will be observed on the following Monday.
 * <br/><br/>The Observable values <b><i>default to true</i></b>.  If set false, the holiday is not observed.<br/><br/>
 * </p>
 * @see org.wrk.date.holiday.USHoliday
 * @see org.wrk.date.holiday.HolidayRules
 * @see org.wrk.date.holiday.HolidayEnum
 * @see org.wrk.date.holiday.Holiday
 * @author Kelly Willard
 */
public class FederalHolidays extends USHoliday implements HolidayRules {
	private SortedSet<Holiday> holidaySet = new TreeSet<>(Comparator.comparing(Holiday::getDate));
	
	private boolean saturdayObservable = true;
	
	private boolean sundayObservable = true;
	
	/**
	 * <p>constructor</p>
	 */
	public FederalHolidays() {
	}

	/**
	 * <p>constructor w/param</p>
	 */
	public FederalHolidays(int year) {
		super(year);
	}

	/**
	 * @return the holidaySet
	 */
	private SortedSet<Holiday> getHolidaySet() {
		return holidaySet;
	}
	
	/**
	 * <p>Initialize the FederalHolidays class.
	 * <li>Calls the loadHolidays method.
	 * <li>Call this method after instantiating this class.
	 * <li>Call this method within an init-method setting for dependency injection.
	 * </p>
	 */
	public void init() {
		this.loadHolidays();
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
	 * <p>Are holidays occurring of a Saturday observable?</p>
	 */
	@Override
	public boolean isSaturdayObservable() {
		return saturdayObservable;
	}

	/**
	 * <p>Are holidays occurring of a Sunday observable?</p>
	 */
	@Override
	public boolean isSundayObservable() {
		return sundayObservable;
	}

	/**
	 * <p>Calculate all federal holidays for the year and load them into the holiday list.</p>
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
	 * <p>Anticipate the possibility that New Years Eve may be a holiday.</p>
	 * @return Holiday if New Years Eve else null.
	 */
	private Holiday newYearsEveHoliday() {
		// Create the next year value.
		int nextYear = this.getYear() + 1;
		
		// Instantiate the object with next year value.
		USHoliday usHoliday = new USHoliday(nextYear);
		
		// Create the next new years day holiday.
		Holiday newYearsEve = this.createHoliday(usHoliday.getNewYearsDay(), HolidayEnum.NEWYEARS_DAY);
		
		// Is the holiday the last day of the current year?
		if(this.isNewYearsEve(newYearsEve)) {
			// Change the holiday enumeration type.
			newYearsEve.setDay(HolidayEnum.NEWYEARS_EVE);
		}
		else {
			// Not New Years Eve.
			newYearsEve = null;
		}
		
		return newYearsEve;
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
	 * 
	 * @return String[] - format "<Date: format "EEEEE MM-dd-yyyy"> <Holiday Enum> <Holiday Name> <Observable flag>"
	 */
	public String[] toHolidays() {
		String[] result = new String[holidaySet.size()];
		
		SimpleDateFormat sdf = new SimpleDateFormat("EEEEE MM-dd-yyyy");

		// Iteration counter.
		int index = 0;
		
		for(Holiday holiday : holidaySet) {
			result[index++] = String.format("%s,%s,%s%s",sdf.format(holiday.getDate().getTime()), holiday.getDay().name(), holiday.getDay().getHolidayName(), (holiday.isObserved() ? ",observed" : ""));
		}
		
		return result;
	}

	/**
	 * <p>Which holiday does the calendar date match?</p>
	 * @param date
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
	 * @param date
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