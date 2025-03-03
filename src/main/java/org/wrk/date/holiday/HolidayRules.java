package org.wrk.date.holiday;

import java.util.Calendar;

/**
 * <h1>HolidayRules</h1>interface provides the U.S. federal holiday business rules and functionality.
 * <h4>Functionality</h4>
 * <ul>
 * <li>isActualDay is the Holiday an actual holiday, meaning if can occur on any day of the week?
 * <li>isNewYearsEve is the date New Years Eve?
 * <li>isObservableOnSaturday is the holiday observable on a Saturday?
 * <li>isObservableOnSunday is the holiday observable on a Sunday?
 * <li>isSaturday does the date occur during Saturday?
 * <li>isSunday does the date occur during Sunday?
 * <li>isWeekend does the date occur during the weekend?
 * </ul>
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
	 * <p>Is the day an actual holiday?<br/>An actual holiday can occur any day of the week.</p>
	 * @param day HolidayEnum value.
	 * @return boolean true if day is an actual holiday else false.
	 */
	default public boolean isActualDay(HolidayEnum day) {
		return day != null ? day == HolidayEnum.CHRISTMAS_DAY || day == HolidayEnum.INDEPENDENCE_DAY || day == HolidayEnum.JUNETEENTH_DAY || day == HolidayEnum.NEWYEARS_DAY || day == HolidayEnum.VETERANS_DAY : false;
	}
	
	/**
	 * <p>Does the date occur on New Years Eve?</p>
	 * @param date value to check.
	 * @return boolean true if date is New Years Eve else false.
	 */
	default public boolean isNewYearsEve(Calendar date) {
		return date != null ? date.get(Calendar.MONTH) == Calendar.DECEMBER && date.get(Calendar.DAY_OF_MONTH) == 31 : false;		
	}
	
	/**
	 * <p>Is the date Saturday and observable?</p>
	 * @param date value to be determined.
	 * @return boolean true if date is Saturday and observable else false.
	 */
	default public boolean isObservableOnSaturday(Calendar date) {
		return this.isSaturday(date) && this.isSaturdayObservable();
	}
	
	/**
	 * <p>Is the date Sunday and observable?</p>
	 * @param date value to be determined.
	 * @return boolean true if date is Sunday and observable else false.
	 */
	default public boolean isObservableOnSunday(Calendar date) {
		return this.isSunday(date) && this.isSundayObservable();
	}
	
	/**
	 * <p>Is the date Saturday?</p>
	 * @param date value to be determined.
	 * @return boolean true if date is Saturday else false.
	 */
	default public boolean isSaturday(Calendar date) {
		return date != null ? date.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY : false;
	}
	
	/**
	 * <p>Are holidays Saturday observable?</p>
	 * @return saturdayObservable boolean set to either true or false.
	 */
	public boolean isSaturdayObservable();
	
	/**
	 * <p>Is the date Sunday?</p>
	 * @param date value to be determined.
	 * @return boolean true if date is Sunday else false.
	 */
	default public boolean isSunday(Calendar date) {
		return date != null ? date.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY : false;
	}
	
	/**
	 * <p>Are holidays Sunday observable?</p>
	 * @return sundayObservable boolean set to either true or false.
	 */
	public boolean isSundayObservable();
	
	/**
	 * <p>Set Saturday observable flag.</p>
	 * @param saturdayObservable boolean set to either true or false.
	 */
	public void setSaturdayObservable(boolean saturdayObservable);
	
	/**
	 * <p>Set Sunday observable flag.</p>
	 * @param sundayObservable boolean set to either true or false.
	 */
	public void setSundayObservable(boolean sundayObservable);
	
	/**
	 * <p>Does the date occur on a weekend?</p>
	 * @param date value to be determined.
	 * @return boolean true if weekend date else false.
	 */
	default public boolean isWeekEnd(Calendar date) {
		return this.isSaturday(date) || this.isSunday(date);
	}
}