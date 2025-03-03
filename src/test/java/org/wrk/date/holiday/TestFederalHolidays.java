package org.wrk.date.holiday;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Calendar;
import java.util.stream.Stream;

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
	public void testChristmasDay() {
		assertDoesNotThrow((() -> this.getFederalHolidays().isHoliday(this.getFederalHolidays().getChristmasDay())),"Christmas day not observed.");
		
		try {
			assertTrue(this.getFederalHolidays().isHoliday(this.getFederalHolidays().getChristmasDay()),"Christmas day not observed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testChristmasDayDate() {
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
	public void testChristmasDayNotObservedOnChristmasDay() {
		Calendar today = Calendar.getInstance();
		
		int iyear = 2022;
		
		today.set(iyear, Calendar.DECEMBER, 25);
		
		FederalHolidays holidays = new FederalHolidays(iyear);
		
		holidays.init();
		
		try {
			assertTrue(holidays.whichHoliday(today) == null, "Holiday is not null.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testChristmasDayObservedMonday() {
		Calendar today = Calendar.getInstance();
		
		int iyear = 2022;
		
		today.set(iyear, Calendar.DECEMBER, 26);
		
		FederalHolidays holidays = new FederalHolidays(iyear);
		
		holidays.init();
		
		try {
			assertTrue(holidays.whichHoliday(today) != null, "Holiday is null.");
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	@Test
	public void testChristmasEve() {
		Calendar today = Calendar.getInstance();
		
		int iyear = 2021;
		
		today.set(iyear,Calendar.DECEMBER,24);
		
		FederalHolidays holidays = new FederalHolidays(iyear);
		
		holidays.init();
		
		try {
			// The holiday should be observed. Christmas is on a Saturday.
			assertTrue(holidays.whichHoliday(today) != null,"Holiday is null.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testColumbusDay() {
		assertDoesNotThrow((() -> this.getFederalHolidays().isHoliday(this.getFederalHolidays().getColumbusDay())),"Columbus day not observed.");
		
		try {
			assertTrue(this.getFederalHolidays().isHoliday(this.getFederalHolidays().getColumbusDay()),"Columbus day not observed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testIndependenceDay() {
		assertDoesNotThrow((() -> this.getFederalHolidays().isHoliday(this.getFederalHolidays().getIndependenceDay())),"Independence day not observed.");
		
		try {
			assertTrue(this.getFederalHolidays().isHoliday(this.getFederalHolidays().getIndependenceDay()),"Independence day not observed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testJuneteenthDay() {
		assertDoesNotThrow((() -> this.getFederalHolidays().isHoliday(this.getFederalHolidays().getJuneteenthDay())),"Juneteenth day not observed.");
		
		try {
			assertTrue(this.getFederalHolidays().isHoliday(this.getFederalHolidays().getJuneteenthDay()),"Juneteenth day not observed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testLaborDay() {
		assertDoesNotThrow((() -> this.getFederalHolidays().isHoliday(this.getFederalHolidays().getLaborDay())),"Labor day not observed.");
		
		try {
			assertTrue(this.getFederalHolidays().isHoliday(this.getFederalHolidays().getLaborDay()),"Labor day not observed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testMartinLutherKingJrDay() {
		assertDoesNotThrow((() -> this.getFederalHolidays().isHoliday(this.getFederalHolidays().getMartinLutherKingJrDay())),"Martin Luther King Jr day not observed.");
		
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
		assertDoesNotThrow((() -> this.getFederalHolidays().isHoliday(this.getFederalHolidays().getMemorialDay())),"Memorial day not observed.");
		
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
		assertDoesNotThrow((() -> this.getFederalHolidays().isHoliday(this.getFederalHolidays().getNewYearsDay())),"New Years day not observed.");
		
		try {
			assertTrue(this.getFederalHolidays().isHoliday(this.getFederalHolidays().getNewYearsDay()),"New Years day not observed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testNewYearsDayMonday() {
		Calendar today = Calendar.getInstance();
		
		int iyear = 2023;
		
		today.set(iyear,Calendar.JANUARY,1);
		
		FederalHolidays federalHolidays = new FederalHolidays(iyear);
		
		federalHolidays.init();
		
//		Stream.of(federalHolidays.toHolidays()).forEach(it -> System.out.println(it));
		
		assertDoesNotThrow((() -> this.getFederalHolidays().isHoliday(this.getFederalHolidays().getNewYearsDay())),"New Years day not observed.");
		
		try {
			assertTrue(this.getFederalHolidays().isHoliday(this.getFederalHolidays().getNewYearsDay()),"New Years day not observed.");
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
		assertDoesNotThrow((() -> this.getFederalHolidays().isHoliday(today)),"New Years eve not observed.");
		
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
		assertDoesNotThrow((() -> this.getFederalHolidays().isHoliday(this.getFederalHolidays().getPresidentsDay())),"Presidents day not observed.");
		
		try {
			assertTrue(this.getFederalHolidays().isHoliday(this.getFederalHolidays().getPresidentsDay()),"Presidents day not observed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testThanksgivingDay() {
		assertDoesNotThrow((() -> this.getFederalHolidays().isHoliday(this.getFederalHolidays().getThanksgivingDay())),"Thanksgiving day not observed.");
		
		try {
			assertTrue(this.getFederalHolidays().isHoliday(this.getFederalHolidays().getThanksgivingDay()),"Thanksgiving day not observed.");
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
	}

	@Test
	public void testToHolidaysNewYearsEve() {
		int iyear = 2021;
		
		FederalHolidays holidays = new FederalHolidays(iyear);
		
		holidays.init();
		
		String[] listHolidays = holidays.toHolidays();
		
		assertTrue(listHolidays.length == 12, "Holidays does not contain 12 days.");
	}

	@Test
	public void testToHolidaysNull() {
		FederalHolidays holidays = new FederalHolidays();
		
		String[] listHolidays = holidays.toHolidays();
		
		assertTrue(listHolidays.length == 0, "There are more than 0 holidays available.");
	}
	
	@Test
	public void testVeteransDay() {
		assertDoesNotThrow((() -> this.getFederalHolidays().isHoliday(this.getFederalHolidays().getVeteransDay())),"Veterans day not observed.");
		
		try {
			assertTrue(this.getFederalHolidays().isHoliday(this.getFederalHolidays().getVeteransDay()),"Veterans day not observed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
}