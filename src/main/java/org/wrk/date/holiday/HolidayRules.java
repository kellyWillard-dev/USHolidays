package org.wrk.date.holiday;

import java.util.Calendar;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * <h3>HolidayRules</h3>interface provides the U.S. federal holiday business rules and functionality.
 * <h4>Functionality</h4>
 * <li>createHoliday create a U.S. Holiday.
 * <li>isActualDay is the Holiday an actual holiday, meaning if can occur on any day of the week?
 * <li>isEnumeratedHoliday is the Holiday a valid holiday listed within HolidayEnum object?
 * <li>isMonday is the day Monday?
 * <li>isNewYearsEve is the day New Years Eve?
 * <li>isObservableOnSaturday is the holiday observable on a Saturday?
 * <li>isObservableOnSunday is the holiday observable on a Sunday?
 * <li>isSaturday does the date occur during Saturday?
 * <li>isSunday does the date occur during Sunday?
 * <li>isWeekend does the date occur during the weekend?
 * <br/>
 * <h4>Weekend Observable Rule</h4>
 * <p>Holidays may occur on the weekend.<br/>
 * The saturdayObservable and sundayObservable flags default to true.<br/>
 * Thus calculating Saturday occurring holidays to be observed on the prior Friday and<br/>
 * Sunday occurring holidays to be observed on the following Monday.<br/><br/>
 * If either flag is set to <b>false</b>, the holiday will not be calculated to be observed and will occur on the actual day.</p>
 * <p>
 * <i>Example</i><br/>
 * The Federal Reserve Bank does not observe Saturday occurring holidays and are open for business on the prior Friday.<br/>
 * Therefore, the saturdayObservable flag is set to false.
 * </p>
 * @author Kelly Willard
 */
public interface HolidayRules {
	
	/**
	 * <p>Create a holiday object from the calendar date and the holiday enumeration.</p>
	 * <p>Because there are 5 actual holidays (meaning they can occur on any day of the week)<br/>
	 * the observed flag is set to false, indicating actual day.</p>
	 * <p>Observable holidays indicate whether or not to observe a holiday on another day if it occurs on a weekend.<br/>
	 * Traditionally, Saturday holidays observe on the prior Friday and Sunday holidays on the following Monday.</p>
	 * @param date
	 * @param day
	 * @return Holiday if valid parameters exist else null.
	 */
	default public Holiday createHoliday(Calendar date, HolidayEnum day) {
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
	 * <p>Is the day an actual holiday?<br/>An actual holiday can occur any day of the week.</p>
	 * @param day
	 * @return true if day is an actual holiday else false.
	 */
	default public boolean isActualDay(HolidayEnum day) {
		return day != null ? day == HolidayEnum.CHRISTMAS_DAY || day == HolidayEnum.INDEPENDENCE_DAY || day == HolidayEnum.JUNETEENTH_DAY || day == HolidayEnum.NEWYEARS_DAY || day == HolidayEnum.VETERANS_DAY : false;
	}
	
	/**
	 * <p>Is the day a valid enumerated holiday?</p>
	 * @param day
	 * @return true if day is within the HolidayEnum list else false.
	 */
	default public boolean isEnumeratedHoliday(HolidayEnum day) {
		Optional<HolidayEnum> holiday = day != null ? Stream.of(HolidayEnum.values()).filter(it -> it == day).findFirst() : Optional.empty();
		
		return holiday.isPresent();
	}
	
	/**
	 * <p>Is the date Monday</p>
	 * @param date
	 * @return true if date is Monday else false.
	 */
	default public boolean isMonday(Calendar date) {
		return date != null ? date.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY : false;
	}
	
	/**
	 * <p>Does the date occur on New Years Eve?</p>
	 * @param date
	 * @return true if date is New Years Eve else false.
	 */
	default public boolean isNewYearsEve(Holiday newYearsEve) {
		return newYearsEve != null && newYearsEve.getDate() != null ? newYearsEve.getDate().get(Calendar.MONTH) == Calendar.DECEMBER && newYearsEve.getDate().get(Calendar.DAY_OF_MONTH) == 31 : false;		
	}
	
	/**
	 * <p>Is the date Saturday and observable?</p>
	 * @param date
	 * @return true if date is Saturday and observable else false.
	 */
	default public boolean isObservableOnSaturday(Calendar date) {
		return this.isSaturday(date) && this.isSaturdayObservable();
	}
	
	/**
	 * <p>Is the date Sunday and observable?</p>
	 * @param date
	 * @return true if date is Sunday and observable else false.
	 */
	default public boolean isObservableOnSunday(Calendar date) {
		return this.isSunday(date) && this.isSundayObservable();
	}
	
	/**
	 * <p>Is the date Saturday?</p>
	 * @param date
	 * @return true if date is Saturday else false.
	 */
	default public boolean isSaturday(Calendar date) {
		return date != null ? date.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY : false;
	}
	
	/**
	 * <p>Are holidays Saturday observable?</p>
	 * @return saturdayObservable flag.
	 */
	public boolean isSaturdayObservable();
	
	/**
	 * <p>Is the date Sunday?</p>
	 * @param date
	 * @return true if date is Sunday else false.
	 */
	default public boolean isSunday(Calendar date) {
		return date != null ? date.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY : false;
	}
	
	/**
	 * <p>Are holidays Sunday observable?</p>
	 * @return sundayObservable flag.
	 */
	public boolean isSundayObservable();
	
	/**
	 * <p>Set Saturday observable flag.</p>
	 * @param saturdayObservable
	 */
	public void setSaturdayObservable(boolean saturdayObservable);
	
	/**
	 * <p>Set Sunday observable flag.</p>
	 * @param sundayObservable
	 */
	public void setSundayObservable(boolean sundayObservable);
	
	/**
	 * <p>Does the date occur on a weekend?</p>
	 * @param date
	 * @return true if weekend date else false.
	 */
	default public boolean isWeekEnd(Calendar date) {
		return this.isSaturday(date) || this.isSunday(date);
	}
}