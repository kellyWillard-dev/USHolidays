package org.wrk.date.holiday;

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
 * Test the federal holidays.
 * 
 * @author Kelly Willard
 */
@ExtendWith(value = { SpringExtension.class })
@ContextConfiguration(locations= {"/test-holiday-application-context.xml"})
public class TestFederalHolidays {

	@Autowired
	private FederalHolidays federalHolidays;
	
	/**
	 * constructor
	 */
	public TestFederalHolidays() {
	}
	
	/**
	 * @return the federalHolidays
	 */
	public FederalHolidays getFederalHolidays() {
		return federalHolidays;
	}

	/**
	 * @param federalHolidays the federalHolidays to set
	 */
	public void setFederalHolidays(FederalHolidays federalHolidays) {
		this.federalHolidays = federalHolidays;
	}
	
	@Test
	public void testChristmasDate() {
		Calendar christmasDay = Calendar.getInstance();
		
		christmasDay.set(christmasDay.get(Calendar.YEAR), Calendar.DECEMBER, 25);
		
		// Determine if the Date value is a holiday.  Both Calendar and Date objects can be used.
		try {
			assertTrue(this.getFederalHolidays().isHoliday(christmasDay.getTime()), "The date is not a holiday.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testChristmasDay() {
		try {
			assertTrue(this.getFederalHolidays().isHoliday(this.getFederalHolidays().getChristmasDay()),"Christmas day not observed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testColumbusDay() {
		try {
			assertTrue(this.getFederalHolidays().isHoliday(this.getFederalHolidays().getColumbusDay()),"Columbus day not observed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testIndependenceDay() {
		try {
			assertTrue(this.getFederalHolidays().isHoliday(this.getFederalHolidays().getIndependenceDay()),"Independence day not observed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testJuneteenthDay() {
		try {
			assertTrue(this.getFederalHolidays().isHoliday(this.getFederalHolidays().getJuneteenthDay()),"Juneteenth day not observed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testLaborDay() {
		try {
			assertTrue(this.getFederalHolidays().isHoliday(this.getFederalHolidays().getLaborDay()),"Labor day not observed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testMartinLutherKingJrDay() {
		try {
			assertTrue(this.getFederalHolidays().isHoliday(this.getFederalHolidays().getMartinLutherKingJrDay()),"Martin Luther King Jr day not observed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testMaximumYear() {
		int maxYear = Calendar.getInstance().getActualMaximum(Calendar.YEAR);
		
		FederalHolidays federalHolidays = new FederalHolidays(maxYear);
		
		assertFalse(maxYear == federalHolidays.getYear(), "Maximum year matches.");
	}
	
	@Test
	public void testMaximumYearMinus1() {
		int maxYear = Calendar.getInstance().getActualMaximum(Calendar.YEAR) - 1;
		
		FederalHolidays federalHolidays = new FederalHolidays(maxYear);
		
		assertTrue(maxYear == federalHolidays.getYear(), "Maximum year does not match.");
	}
	
	@Test
	public void testMemorialDay() {
		try {
			assertTrue(this.getFederalHolidays().isHoliday(this.getFederalHolidays().getMemorialDay()),"Memorial day not observed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testMinimumYear() {
		int minYear = -1;
		
		FederalHolidays federalHolidays = new FederalHolidays(minYear);
		
		assertTrue(Calendar.getInstance().get(Calendar.YEAR) == federalHolidays.getYear(), "Minimum year does not match.");
	}
	
	@Test
	public void testNewYearsDay() {
		try {
			assertTrue(this.getFederalHolidays().isHoliday(this.getFederalHolidays().getNewYearsDay()),"NewYears day not observed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testNewYearsEve() {
		Calendar today = Calendar.getInstance();
		
		int iyear = 2021;
		
		today.set(iyear,Calendar.DECEMBER,31);
		
		FederalHolidays federalHolidays = new FederalHolidays(iyear);
		
		federalHolidays.init();

		// Is New Years Eve a holiday?
		try {
			assertTrue(federalHolidays.isHoliday(today), "Holiday is not New Years Eve.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testNullHoliday() {
		Calendar today = null;
		
		FederalHolidays federalHolidays = new FederalHolidays();
		
		federalHolidays.init();
		
		assertThrows(Exception.class,() -> federalHolidays.isHoliday(today), "Date is null.");
	}

	@Test
	public void testNullHolidayList() {
		Calendar today = Calendar.getInstance();
		
		int iyear = 2021;
		
		today.set(iyear,Calendar.DECEMBER,31);
		
		FederalHolidays federalHolidays = new FederalHolidays(iyear);

		assertThrows(Exception.class,() -> federalHolidays.isHoliday(today), "Holiday is not initiated.");
	}

	@Test
	public void testNullHolidayListAndDate() {
		Calendar today = null;
				
		FederalHolidays federalHolidays = new FederalHolidays();
		
		assertThrows(Exception.class,() -> federalHolidays.isHoliday(today), "Holiday is not initiated and Date is null.");
	}
	
	@Test
	public void testPresidentsDay() {
		try {
			assertTrue(this.getFederalHolidays().isHoliday(this.getFederalHolidays().getPresidentsDay()),"Presidents day not observed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testThanksgivingDay() {
		try {
			assertTrue(this.getFederalHolidays().isHoliday(this.getFederalHolidays().getThanksgivingDay()),"Thanksgiving day not observed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testToday() {
		Holiday holiday;
		try {
			holiday = this.getFederalHolidays().whichHoliday(Calendar.getInstance());
			
			if(holiday != null) {
				assertTrue(holiday != null, "Holiday is null!");
				System.out.println(String.format("Today is %s.", holiday.getDay().getHolidayName()));
			} else {
				assertTrue(holiday == null, "Holiday is not null!");
				System.out.println("Today is not a holiday.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testToHolidays() {
		FederalHolidays holidays = new FederalHolidays();
		
		holidays.init();
		
		String[] listHolidays = holidays.toHolidays();
		
		assertFalse(listHolidays.length == 0, "There are more than 0 holidays available.");
		
		for(String line : listHolidays) {
			System.out.println(line);
		}
	}

	@Test
	public void testToHolidaysNull() {
		FederalHolidays holidays = new FederalHolidays();
		
		String[] listHolidays = holidays.toHolidays();
		
		assertTrue(listHolidays.length == 0, "There are more than 0 holidays available.");
		
		for(String line : listHolidays) {
			System.out.println(line);
		}
	}

	@Test
	public void testToHolidays2021() {
		int iyear = 2021;
		
		FederalHolidays holidays = new FederalHolidays(iyear);
		
		holidays.init();
		
		String[] listHolidays = holidays.toHolidays();
		
		assertTrue(listHolidays.length == 12, "The 12 holidays not available.");
		
		for(String line : listHolidays) {
			System.out.println(line);
		}
	}

	@Test
	public void testToHolidays2027() {
		int iyear = 2027;
		
		FederalHolidays holidays = new FederalHolidays(iyear);
		
		holidays.init();
		
		String[] listHolidays = holidays.toHolidays();
		
		assertTrue(listHolidays.length == 12, "The 12 holidays not available.");
		
		for(String line : listHolidays) {
			System.out.println(line);
		}
	}
	
	@Test
	public void testVeteransDay() {
		try {
			assertTrue(this.getFederalHolidays().isHoliday(this.getFederalHolidays().getVeteransDay()),"Veterans day not observed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
}