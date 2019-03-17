package transportManagement.supportClasses;

public class MyDate implements Comparable<MyDate> {

	private final int day, month, year;
	
	public MyDate( int d, int m, int y ) { day = d; month = m; year = y; }
	
	public int getDay() { return day; }
	public int getMonth() { return month; }
	public int getYear() { return year; }
	
	@Override
	public String toString() { return day + "/" + month + "/" + year; }
	
	@Override
	public int compareTo(MyDate that) {
		if( this.year != that.year )
			return this.year - that.year;
		else if( this.month != that.month )
			return this.month - that.month;
		else
			return this.day - that.day;
	}

}
