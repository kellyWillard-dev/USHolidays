package org.wrk.date.holiday;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Test holidays that are marked unobserved.
 */
@ExtendWith(value = { SpringExtension.class })
@ContextConfiguration(locations= {"/test-holiday-application-context.xml"})
class TestHolidaysUnobserved {

	@Autowired
	private Holidays corpHolidays;
	
	private USHoliday usHoliday = new USHoliday();
	
	/**
	 * @return the corpHolidays
	 */
	public Holidays getCorpHolidays() {
		return corpHolidays;
	}

	/**
	 * @return the usHoliday
	 */
	public USHoliday getUsHoliday() {
		return usHoliday;
	}

	/**
	 * @param corpHolidays the corpHolidays to set
	 */
	public void setCorpHolidays(Holidays corpHolidays) {
		this.corpHolidays = corpHolidays;
	}
	
	@Test
	void testColumbusDay() {
		try {
			assertFalse(this.getCorpHolidays().isHoliday(this.getUsHoliday().getColumbusDay()),"Columbus day is observed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testJuneteenthDay() {
		try {
			assertFalse(this.getCorpHolidays().isHoliday(this.getUsHoliday().getJuneteenthDay()),"Juneteenth day is observed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testMartinLutherKingJrDay() {
		try {
			assertFalse(this.getCorpHolidays().isHoliday(this.getUsHoliday().getMartinLutherKingJrDay()),"Martin Luther King Jr day is observed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testPresidentsDay() {
		try {
			assertFalse(this.getCorpHolidays().isHoliday(this.getUsHoliday().getPresidentsDay()),"Presidents day is observed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testVeteransDay() {
		try {
			assertFalse(this.getCorpHolidays().isHoliday(this.getUsHoliday().getVeteransDay()),"Veterans day is observed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}