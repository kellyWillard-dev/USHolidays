package org.wrk.date.holiday;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * 
 */
@ExtendWith(value = { SpringExtension.class })
@ContextConfiguration(locations= {"/test-holiday-application-context.xml"})
class TestHolidayRules {

	@Autowired
	private HolidayRules frbHolidays;

	/**
	 * Position the date to the desired DAY_OF_WEEK.
	 * 
	 * @param date
	 * @param dayOfWeek
	 * @return
	 */
	private Calendar createDayOfWeek(Calendar date, int dayOfWeek) {
		int addDays = dayOfWeek - date.get(Calendar.DAY_OF_WEEK);
		date.add(Calendar.DAY_OF_MONTH, addDays);
		return date;
	}
	
	@Test
	void testIsActualHoliday() {
		assertTrue(frbHolidays.isActualDay(HolidayEnum.CHRISTMAS_DAY), "Christmas day is not actual holiday.");
	}
	
	@Test
	void testIsNewYearsEve() {
		USHoliday h = new USHoliday(2022);
		
		Calendar today = h.getNewYearsDay();
		today.add(Calendar.DAY_OF_MONTH, -1);
		
		Holiday newYearsEve = new Holiday();
		
		newYearsEve.setDate(today);
		newYearsEve.setDay(HolidayEnum.NEWYEARS_EVE);
		
		assertTrue(frbHolidays.isNewYearsEve(newYearsEve.getDate()), "Today is not New Years Eve.");		
	}	
	
	@Test
	void testIsNotActualHoliday() {
		assertFalse(frbHolidays.isActualDay(HolidayEnum.MARTINLUTHERKINGJR_DAY), "MARTIN LUTHER KING JR day is actual holiday.");
	}
	
	@Test
	void testIsNotNewYearsEve() {
		USHoliday h = new USHoliday(2022);
		
		Calendar today = h.getNewYearsDay();
		today.add(Calendar.DAY_OF_MONTH, -2);
		
		Holiday newYearsEve = new Holiday();
		
		newYearsEve.setDate(today);
		newYearsEve.setDay(HolidayEnum.NEWYEARS_EVE);
		
		assertFalse(frbHolidays.isNewYearsEve(newYearsEve.getDate()), "Today is New Years Eve.");		
	}	
	
	@Test
	void testIsNotSaturday() {
		Calendar today = this.createDayOfWeek(Calendar.getInstance(), Calendar.WEDNESDAY);
		
		assertFalse(frbHolidays.isSaturday(today), "Today is Saturday.");
	}
	
	@Test
	void testIsNotSunday() {
		Calendar today = this.createDayOfWeek(Calendar.getInstance(), Calendar.THURSDAY);
		
		assertFalse(frbHolidays.isSunday(today), "Today is Sunday.");
	}
	
	@Test
	void testIsNotWeekend() {
		Calendar today = this.createDayOfWeek(Calendar.getInstance(), Calendar.TUESDAY);
		
		assertFalse(frbHolidays.isWeekEnd(today), "Today is a weekend date.");
	}
	
	@Test
	void testIsSaturday() {
		Calendar today = this.createDayOfWeek(Calendar.getInstance(), Calendar.SATURDAY);
		
		assertTrue(frbHolidays.isSaturday(today), "Today is not Saturday.");
	}
	
	@Test
	void testIsSunday() {
		Calendar today = this.createDayOfWeek(Calendar.getInstance(), Calendar.SUNDAY);
		
		assertTrue(frbHolidays.isSunday(today), "Today is not Sunday.");
	}
	
	@Test
	void testIsWeekend() {
		Calendar today = this.createDayOfWeek(Calendar.getInstance(), Calendar.SUNDAY);
		
		assertTrue(frbHolidays.isWeekEnd(today), "Today is not a weekend date.");
	}
}
