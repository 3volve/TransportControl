package transportManagement.supportClasses;

public class MyDate implements Comparable<MyDate> {

	private final int day, month, year;
	
	public MyDate( int m, int d, int y ) { month = m; day = d; year = y; }
	
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
