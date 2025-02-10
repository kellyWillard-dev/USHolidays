package org.wrk.date.holiday;

import java.util.Calendar;
import java.util.Date;

/**
 * <H1>Holidays</H1>interface specifies holiday functionality.
 * <ul>
 * <li>isHoliday for Calendar object.
 * <li>isHoliday for Date object.
 * <li>whichHoliday for Calendar object.
 * <li>whichHoliday for Date object.
 * </ul>
 */
public interface Holidays {

	public boolean isHoliday(Calendar date) throws Exception;
	
	public boolean isHoliday(Date date) throws Exception;
	
	public Holiday whichHoliday(Calendar date) throws Exception;
	
	public Holiday whichHoliday(Date date) throws Exception;
}