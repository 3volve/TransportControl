package transportManagement;

import java.util.HashSet;

import transportManagement.supportClasses.MyDate;

public class CruiseShip {
	private HashSet<MyDate[]> trips;
	private String name;
	
	CruiseShip( String n ) { name = n; }
	
	boolean bookShip( MyDate depart, MyDate arrive ) {
		for( MyDate[] booking : trips )
			if( (depart.isBetween(booking[0], booking[1]) || arrive.isBetween(booking[0], booking[1])) ||
				(depart.compareTo(booking[0]) < 0 && arrive.compareTo(booking[1]) > 0) )
					return false;
		
		MyDate[] newBooking = new MyDate[] {depart, arrive};
		trips.add(newBooking);
		return true;
	}
}
