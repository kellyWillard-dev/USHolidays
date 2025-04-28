package org.wrk.date.holiday;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Calendar;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(value = { SpringExtension.class })
@ContextConfiguration(locations= {"/test-holiday-application-context.xml"})
class TestFederalReserveBoard {
	
	@Autowired
	private ObservedHolidays frbHolidays;
	
	@Test
	void testNewYearsEve() {
		Calendar today = Calendar.getInstance();
		
		int iyear = 2027;
		
		today.set(iyear,Calendar.DECEMBER,31);
		
		ObservedHolidays holidays = frbHolidays.clone(iyear);
		
		holidays.init();
		
		try {
			// The holiday should not be observed. NewYearsEve is on a Friday.
			assertTrue(holidays.whichHoliday(today) == null,"Holiday is not null.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Stream.of(holidays.toHolidays()).forEach(it -> System.out.println(it));
		assertTrue(holidays.toHolidays().length == 11, "Holiday array not equal to 11");
	}
	
	@Test
	void testNewYearsEveObserved() {
		Calendar today = Calendar.getInstance();
		
		int iyear = 2027;
		
		today.set(iyear,Calendar.DECEMBER,31);
		
		FederalHolidays federalHolidays = new FederalHolidays(iyear);
		
		federalHolidays.init();
		
//		Stream.of(federalHolidays.toHolidays()).forEach(it -> System.out.println(it));
		
		try {
			// The holiday should be observed. NewYearsEve is on a Friday.
			assertTrue(federalHolidays.whichHoliday(today) != null,"Holiday is null.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Stream.of(federalHolidays.toHolidays()).forEach(it -> System.out.println(it));
		assertTrue(federalHolidays.toHolidays().length == 12, "Holiday array not equal to 12");
	}
}