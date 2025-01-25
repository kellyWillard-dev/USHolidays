package org.wrk.date.holiday;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * 
 * @author Kelly Willard
 */
@ExtendWith(value = { SpringExtension.class })
@ContextConfiguration(locations= {"/test-holiday-application-context.xml"})
public class TestCorpHolidays {

	@Autowired
	private ObservedHolidays corpHolidays;

	//  Time stamp:  HH:mm:SS
	private SimpleDateFormat sdf = new SimpleDateFormat("EEEEE MMMMM dd yyyy");

	/**
	 * @return the corpHolidays
	 */
	public ObservedHolidays getCorpHolidays() {
		return corpHolidays;
	}

	/**
	 * @return the sdf
	 */
	public SimpleDateFormat getSdf() {
		return sdf;
	}

	/**
	 * @param corpHolidays the corpHolidays to set
	 */
	public void setCorpHolidays(ObservedHolidays corpHolidays) {
		this.corpHolidays = corpHolidays;
	}

	/**
	 * @param sdf the sdf to set
	 */
	public void setSdf(SimpleDateFormat sdf) {
		this.sdf = sdf;
	}
	
	@Test
	public void testColumbusDay() {
		Calendar today = this.getCorpHolidays().getColumbusDay();
		
		try {
			Holiday holiday = this.getCorpHolidays().whichHoliday(today);
			
			assertTrue(holiday == null, String.format("%s is a holiday.", sdf.format(today.getTime())));
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	@Test
	public void testJuneteenthDay() {
		Calendar today = Calendar.getInstance();
		
		today.set(today.get(Calendar.YEAR), Calendar.JUNE, 19);
		
		try {
			Holiday holiday = this.getCorpHolidays().whichHoliday(today);
			
			assertTrue(holiday == null, String.format("%s is a holiday.", sdf.format(today.getTime())));
		}
		catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	@Test
	public void testMartinLutherKingJrDay() {
		Calendar today = this.getCorpHolidays().getMartinLutherKingJrDay();
		
		try {
			Holiday holiday = this.getCorpHolidays().whichHoliday(today);
			
			assertTrue(holiday == null, String.format("%s is a holiday.", sdf.format(today.getTime())));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testPresidentsDay() {
		Calendar today = this.getCorpHolidays().getPresidentsDay();
		
		try {
			Holiday holiday = this.getCorpHolidays().whichHoliday(today);
			
			assertTrue(holiday == null, String.format("%s is a holiday.", sdf.format(today.getTime())));
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	@Test
	public void testVeteransDay() {
		Calendar today = this.getCorpHolidays().getVeteransDay();
		
		try {
			Holiday holiday = this.getCorpHolidays().whichHoliday(today);
			
			assertTrue(holiday == null, String.format("%s is a holiday.", sdf.format(today.getTime())));
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}