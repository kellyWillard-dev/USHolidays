package org.wrk.date.holiday;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Calendar;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Test Observed holidays.
 * 
 * @author Kelly Willard
 */
@ExtendWith(value = { SpringExtension.class })
@ContextConfiguration(locations= {"/test-holiday-application-context.xml"})
public class TestObservedHolidays {

	@Autowired
	private ObservedHolidays observedHolidays;
	
	/**
	 * constructor
	 */
	public TestObservedHolidays() {
	}
	
	/**
	 * @return the observedHolidays
	 */
	public ObservedHolidays getObservedHolidays() {
		return observedHolidays;
	}
	
	/**
	 * @param observedHolidays the observedHolidays to set
	 */
	public void setObservedHolidays(ObservedHolidays observedHolidays) {
		this.observedHolidays = observedHolidays;
	}
	
	@Test
	public void testClone() {
		Calendar today = Calendar.getInstance();
		
		Integer iyear = today.get(Calendar.YEAR) + 1;
		
		ObservedHolidays nextHolidays = this.getObservedHolidays().clone(iyear);
		
		String[] holidays = nextHolidays.toHolidays();
		
		assertTrue(0 < holidays.length,"Clone holidays failed.");
		
		for(String line : holidays) {
			assertTrue(line.contains(iyear.toString()),String.format("Clone does not contain year %d", iyear));
		}
	}
	
	@Test
	public void testIsUnobservedEmpty() {
		assertFalse(this.getObservedHolidays().isUnobserved("LABOR_DAY"),"Labor day is observed.");
	}
	
	@Test
	public void testIsUnobservedInvalidHolidayName() {
		assertFalse(this.getObservedHolidays().isUnobserved("BOXING_DAY"),"Invalid day is observed.");
	}
	
	@Test
	public void testIsUnobservedNull() {
		assertFalse(this.getObservedHolidays().isUnobserved(null),"Null observed.");
	}
	
	@Test
	public void testRemoveChristmasDay() {		
		assertDoesNotThrow(() -> this.getObservedHolidays().holidayUnobserved(HolidayEnum.CHRISTMAS_DAY.name()), "Could not mark holiday unobserved.");
		
		try {
			assertFalse(this.getObservedHolidays().isHoliday(this.getObservedHolidays().getChristmasDay()),"Christmas day observed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRemoveColumbusDay() {
		assertDoesNotThrow(() -> this.getObservedHolidays().holidayUnobserved(HolidayEnum.COLUMBUS_DAY.name()), "Could not mark holiday unobserved.");
		
		try {
			assertFalse(this.getObservedHolidays().isHoliday(this.getObservedHolidays().getColumbusDay()),"Columbus day observed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRemoveIndependenceDay() {
		assertDoesNotThrow(() -> this.getObservedHolidays().holidayUnobserved(HolidayEnum.INDEPENDENCE_DAY.name()), "Could not mark holiday unobserved.");
		
		try {
			assertFalse(this.getObservedHolidays().isHoliday(this.getObservedHolidays().getIndependenceDay()),"Independence day observed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRemoveInvalidHolidayName() {		
		assertThrows(Exception.class, () -> this.getObservedHolidays().holidayUnobserved("BOXING_DAY"), "Invalid holiday to remove.");
	}
	
	@Test
	public void testRemoveJuneteenthDay() {		
		assertDoesNotThrow(() -> this.getObservedHolidays().holidayUnobserved(HolidayEnum.JUNETEENTH_DAY.name()), "Valid holiday to remove.");
		
		try {
			assertFalse(this.getObservedHolidays().isHoliday(this.getObservedHolidays().getJuneteenthDay()),"Juneteenth day observed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRemoveLaborDay() {
		assertDoesNotThrow(() -> this.getObservedHolidays().holidayUnobserved(HolidayEnum.LABOR_DAY.name()), "Could not mark holiday unobserved.");
		
		try {
			assertFalse(this.getObservedHolidays().isHoliday(this.getObservedHolidays().getLaborDay()),"Labor day observed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRemoveMartinLutherKingJrDay() {
		assertDoesNotThrow(() -> this.getObservedHolidays().holidayUnobserved(HolidayEnum.MARTINLUTHERKINGJR_DAY.name()), "Could not mark holiday unobserved.");
		
		try {
			assertFalse(this.getObservedHolidays().isHoliday(this.getObservedHolidays().getMartinLutherKingJrDay()),"Martin Luther King Jr day observed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRemoveMemorialDay() {
		assertDoesNotThrow(() -> this.getObservedHolidays().holidayUnobserved(HolidayEnum.MEMORIAL_DAY.name()), "Could not mark holiday unobserved.");
		
		try {
			assertFalse(this.getObservedHolidays().isHoliday(this.getObservedHolidays().getMemorialDay()),"Memorial day observed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRemoveNewYearsDay() {
		assertDoesNotThrow(() -> this.getObservedHolidays().holidayUnobserved(HolidayEnum.NEWYEARS_DAY.name()), "Could not mark holiday unobserved.");
		
		try {
			assertFalse(this.getObservedHolidays().isHoliday(this.getObservedHolidays().getNewYearsDay()),"NewYears day observed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRemoveNullHolidayName() {
		try {
			assertFalse(this.getObservedHolidays().holidayUnobserved(null),"Null observed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRemovePresidentsDay() {
		assertDoesNotThrow(() -> this.getObservedHolidays().holidayUnobserved(HolidayEnum.PRESIDENTS_DAY.name()), "Could not mark holiday unobserved.");
		
		try {
			assertFalse(this.getObservedHolidays().isHoliday(this.getObservedHolidays().getPresidentsDay()),"Presidents day observed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRemoveThanksgivingDay() {
		assertDoesNotThrow(() -> this.getObservedHolidays().holidayUnobserved(HolidayEnum.THANKSGIVING_DAY.name()), "Could not mark holiday unobserved.");
		
		try {
			assertFalse(this.getObservedHolidays().isHoliday(this.getObservedHolidays().getThanksgivingDay()),"Thanksgiving day observed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRemoveVeteransDay() {
		assertDoesNotThrow(() -> this.getObservedHolidays().holidayUnobserved(HolidayEnum.VETERANS_DAY.name()), "Could not mark holiday unobserved.");
		
		try {
			assertFalse(this.getObservedHolidays().isHoliday(this.getObservedHolidays().getVeteransDay()),"Veterans day observed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}