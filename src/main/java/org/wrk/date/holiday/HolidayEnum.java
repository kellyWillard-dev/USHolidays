package org.wrk.date.holiday;

/**
 * <h3>HolidayEnum</h3>enumerated U.S federal holidays.
 * <h4>Enumerations List</h4>
 * <p>
 * <li>CHRISTMAS_DAY
 * <li>COLUMBUS_DAY
 * <li>INDEPENDENCE_DAY
 * <li>JUNETEENTH_DAY
 * <li>LABOR_DAY
 * <li>MARTINLUTHERKINGJR_DAY
 * <li>MEMORIAL_DAY
 * <li>NEWYEARS_DAY
 * <li>NEWYEARS_EVE
 * <li>PRESIDENTS_DAY
 * <li>THANKSGIVING_DAY
 * <li>VETERANS_DAY
 * </p>
 * <p><b><i>Caveat</i></b><br/>There is a possibility that NEW YEARS EVE may be recognized as a holiday.<p/>
 * @author Kelly Willard
 * @see org.wrk.date.holiday.USHoliday
 */
public enum HolidayEnum {
	CHRISTMAS_DAY("Christmas day"),
	COLUMBUS_DAY("Columbus day"),
	INDEPENDENCE_DAY("Independence day"),
	JUNETEENTH_DAY("Juneteenth day"),
	LABOR_DAY("Labor day"),
	MARTINLUTHERKINGJR_DAY("Martin Luther King Jr day"),
	MEMORIAL_DAY("Memorial day"),
	NEWYEARS_DAY("New Years day"),
	NEWYEARS_EVE("New Years eve"),
	PRESIDENTS_DAY("Presidents day"),
	THANKSGIVING_DAY("Thanksgiving day"),
	VETERANS_DAY("Veterans day");
	
	private int holidayValue;
	
	private String holidayName;
	
	HolidayEnum() {
	}
	
	HolidayEnum(String value) {
		this.holidayName = value;
	}

	public String getHolidayName() {
		return this.holidayName;
	}
	
	public int valueOf() {
		return this.holidayValue;
	}
}