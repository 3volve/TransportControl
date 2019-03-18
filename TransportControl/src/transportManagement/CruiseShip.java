package transportManagement;

import java.util.HashMap;
import java.util.HashSet;

import transportManagement.supportClasses.MyDate;
import transportManagement.supportClasses.TransportClass;

public class CruiseShip {
	private HashSet<MyDate[]> trips;
	private HashMap<TransportClass, TransportSection> cabins;
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
	
	boolean setCabins( TransportSection[] sections ) {
		boolean result = true;
		
		for( TransportSection section : sections ) {
			if( !setCabin(section) )
				result = false;
		}
		
		return result;
	}
	
	boolean setCabin( TransportSection section ) {
		if( cabins.containsKey(section.getTransportClass()) )
			return false;
		
		cabins.put(section.getTransportClass(), section);
		return true;
	}
	
	HashMap<TransportClass, TransportSection> getLayout() { return cabins; }
	
	@Override
	public String toString() { return name; }
}
