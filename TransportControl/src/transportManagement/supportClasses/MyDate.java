package transportManagement.supportClasses;

public class MyDate implements Comparable<MyDate> {

	private final int day, month, year;
	
	public MyDate( int d, int m, int y ) { day = d; month = m; year = y; }
	
	public int getDay() { return day; }
	public int getMonth() { return month; }
	public int getYear() { return year; }
	public int dayDifference( MyDate that ) {
		int result = 0, tempDay = 0;
		if( month == that.month )
			result = Math.abs(day - that.day);
		else if( month < that.month ) {
			tempDay = day;
			for( int i = month; month < that.month; i++ )
				tempDay += 30;
			result = tempDay - that.day;
		}
		else if( month > that.month ) {
			tempDay = that.day;
			for( int i = that.month; month > that.month; i++ )
				tempDay += 30;
			result = tempDay - day;
		}
		
		return result;
	}
	
	@Override
	public String toString() { return day + "/" + month + "/" + year; }
	
	@Override
	public int compareTo( MyDate that ) {
		if( this.year != that.year )
			return this.year - that.year;
		else if( this.month != that.month )
			return this.month - that.month;
		else
			return this.day - that.day;
	}

}
