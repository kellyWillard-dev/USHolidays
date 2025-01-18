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
	public void testAllCorpHolidays() {
		for(String line : this.getCorpHolidays().toHolidays()) {
			System.out.println(line);
		}
	}

	@Test
	public void testChristmasEve2021() {
		Calendar today = Calendar.getInstance();
		
		int iyear = 2021;
		
		today.set(iyear,Calendar.DECEMBER,24);
		
		ObservedHolidays holidays = this.getCorpHolidays().clone(iyear);
		
		try {
			// Christmas 2021 is on a Saturday.
			Holiday holiday = holidays.whichHoliday(today);
			// The holiday should not be observed.
			assertTrue(holiday != null, "Holiday is null!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test2022ChristmasDayNotObserved() {
		Calendar today = Calendar.getInstance();
		
		int iyear = 2022;
		
		ObservedHolidays holidays = this.getCorpHolidays().clone(iyear);
		
		today.set(iyear, Calendar.DECEMBER, 25);
		
		try {
			Holiday holiday = holidays.whichHoliday(today);
			
			assertTrue(holiday == null, String.format("%s is a holiday.", sdf.format(today.getTime())));
			
			Holiday christmas = holidays.createHoliday(holidays.getChristmasDay(), HolidayEnum.CHRISTMAS_DAY);
			
			System.out.println(String.format("Christmas day %s occurs on %s", sdf.format(today.getTime()), sdf.format(christmas.getDate().getTime())));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test2022ChristmasDayObserved() {
		Calendar today = Calendar.getInstance();
		
		int iyear = 2022;
		
		today.set(iyear, Calendar.DECEMBER, 26);
		
		ObservedHolidays holidays = this.getCorpHolidays().clone(iyear);
		
		try {
			Holiday holiday = holidays.whichHoliday(today);
			
			assertTrue(holiday != null, String.format("%s is a holiday.", sdf.format(today.getTime())));
			
			System.out.println(String.format("Christmas day %s on %s", holiday.isObserved() ? "is observed" : "occurs", sdf.format(today.getTime())));
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	@Test
	public void test2023ChristmasDayObserved() {
		Calendar today = Calendar.getInstance();

		int iyear = 2023;
		
		today.set(iyear, Calendar.DECEMBER, 25);
		
		ObservedHolidays holidays = this.getCorpHolidays().clone(iyear);
		
		System.out.println(String.format("Today %s.", sdf.format(today.getTime())));
		
		try {
			Holiday holiday = holidays.whichHoliday(holidays.deleteTimestamp(today));
			
			assertTrue(holiday != null, String.format("%s is not a holiday.", sdf.format(today.getTime())));
			
			System.out.println(String.format("Christmas day %s on %s", holiday.isObserved() ? "is observed" : "occurs", sdf.format(today.getTime())));
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	@Test
	public void testColumbusDay() {
		Calendar today = this.getCorpHolidays().getColumbusDay();
		
		try {
			Holiday holiday = this.getCorpHolidays().whichHoliday(today);
			
			assertTrue(holiday == null, String.format("%s is a holiday.", sdf.format(today.getTime())));
			
			System.out.println(String.format("On %s, %s, will employees eat spaghetti? %s.", sdf.format(today.getTime()),HolidayEnum.COLUMBUS_DAY.getHolidayName(),holiday == null ? "Nope" : "Yep"));
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
			
			System.out.println(String.format("New employee is cleared to start on %s", sdf.format(today.getTime())));
		}
		catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	/* */ 
	@Test
	public void testListHolidays() {
		for(String line : this.getCorpHolidays().toHolidays()) {
			System.out.println(line);
		}
	}
	
	@Test
	public void testMartinLutherKingJrDay() {
		Calendar today = this.getCorpHolidays().getMartinLutherKingJrDay();
		
		try {
			Holiday holiday = this.getCorpHolidays().whichHoliday(today);
			
			assertTrue(holiday == null, String.format("%s is a holiday.", sdf.format(today.getTime())));
			
			System.out.println(String.format("Am I going to attend the Martin Luther King Jr day parade?  %s.", (holiday != null ? "Too cold to march" : "Nope")));
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
			
			System.out.println(String.format("Will I be able to go shopping for a gift on %s, %s? %s.", sdf.format(today.getTime()),HolidayEnum.PRESIDENTS_DAY.getHolidayName(),holiday == null ? "Nope" : "Yep"));
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
			
			System.out.println(String.format("Will I be able to march in the Veterans parade on %s, %s? %s.", sdf.format(today.getTime()),HolidayEnum.VETERANS_DAY.getHolidayName(),holiday == null ? "Nope" : "Yep"));
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}