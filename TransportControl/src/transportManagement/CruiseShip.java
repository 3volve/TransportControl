package transportManagement;

import java.util.HashMap;
import java.util.HashSet;

import transportManagement.supportClasses.MyDate;
import transportManagement.supportClasses.CabinClass;

public class CruiseShip {
	private HashSet<MyDate[]> trips;
	private HashMap<CabinClass, CruiseSection> cabins;
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
	
	boolean setCabins( CruiseSection... cabin ) {
		boolean result = true;
		
		for( CruiseSection section : cabin )
			if( cabins.containsKey(section.getTransportClass()) )
				result = false;
		return result; 
	}
	
	@Override
	public String toString() { return name; }
}
