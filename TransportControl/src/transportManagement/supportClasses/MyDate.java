package transportManagement.supportClasses;

import java.util.Calendar;
import java.util.TimeZone;

public class MyDate implements Comparable<MyDate> {

	private final int minute, hour, day, month, year;
	private static MyDate CUR_DATE;
	
	public MyDate( int mi, int hr, int mo, int d, int y ) {
		minute = mi; hour = hr; month = mo; day = d; year = y;
		
		setCurrentDate();
	}
	
	public MyDate( int mo, int d, int y ) {
		minute = 59; hour = 24; month = mo; day = d; year = y;
	}

	public int getMinute() { return minute; }
	public int getHour() { return hour; }
	public int getDay() { return day; }
	public int getMonth() { return month; }
	public int getYear() { return year; }
	
	public int dayDifference( MyDate that ) {
		int result = 0, tempDay = 0;
		if( month == that.month )
			result = Math.abs(day - that.day);
		else if( month < that.month ) {
			tempDay = day;
			for( int i = month; i < that.month; i++ )
				tempDay += 30;
			result = tempDay - that.day;
		}
		else if( month > that.month ) {
			tempDay = that.day;
			for( int i = that.month; i < month; i++ )
				tempDay += 30;
			result = tempDay - day;
		}
		
		return result;
	}
	
	public boolean isBetween( MyDate first, MyDate second ) {
		if( this.compareTo(first) > 0 && this.compareTo(second) < 0 ) return true;
		
		return false;
	}
	
	public boolean isValid() {
		int maxMonths = 12, maxDays = 31, maxHours = 24, maxMinutes = 59;
		
		if( day <= 0 || month <= 0 || hour < 0 || minute < 0 ) return false;
		if( day > maxDays || month > maxMonths || hour > maxHours || minute > maxMinutes ) return false;
		
		return true;
	}
	
	public void setCurrentDate() {
		if( CUR_DATE != null ) return;
		
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		CUR_DATE = new MyDate(
				calendar.get(Calendar.DAY_OF_MONTH),
				calendar.get(Calendar.MONTH) + 1,
				calendar.get(Calendar.YEAR)
			);
	}
	
	public int compareToPresent() { return this.compareTo(CUR_DATE); }
	
	public String toFileString() { return year + ", " + month + ", " + day + ", " + hour + ", " + minute; }
	
	@Override
	public String toString() { return hour + ":" + minute + ", " + month + "/" + day + "/" + year; }
	
	@Override
	public int compareTo( MyDate that ) {
		if( this.year != that.year )
			return this.year - that.year;
		else if( this.month != that.month )
			return this.month - that.month;
		else if( this.day != that.day )
			return this.day - that.day;
		else if( this.hour != that.hour )
			return this.hour - that.hour;
		else
			return this.minute - that.minute;
	}

}
