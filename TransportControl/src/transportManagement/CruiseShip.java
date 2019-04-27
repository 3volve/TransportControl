package transportManagement;

import java.util.HashMap;
import java.util.HashSet;

import transportManagement.supportClasses.MyDate;
import transportManagement.supportClasses.TransportClass;

public class CruiseShip {
	private HashSet<MyDate[]> trips;
	private HashMap<TransportClass, TransportSection> cabins;
	private String name;
	
	CruiseShip( String n ) {
		trips = new HashSet<MyDate[]>();
		cabins = new HashMap<TransportClass, TransportSection>();
		name = n;
	}
	
	void bookShip( MyDate depart, MyDate arrive ) {
		if( !trips.isEmpty() )
			for( MyDate[] booking : trips )
				if( (depart.isBetween(booking[0], booking[1]) || arrive.isBetween(booking[0], booking[1])) ||
					(depart.compareTo(booking[0]) < 0 && arrive.compareTo(booking[1]) > 0) )
						return;
			
		MyDate[] newBooking = new MyDate[] {depart, arrive};
		trips.add(newBooking);
	}
	
	boolean isAvailable(MyDate depart, MyDate arrive ) {
		if( !trips.isEmpty() )
			for( MyDate[] booking : trips )
				if( (depart.isBetween(booking[0], booking[1]) || arrive.isBetween(booking[0], booking[1])) ||
					(depart.compareTo(booking[0]) < 0 && arrive.compareTo(booking[1]) > 0) )
						return false;
		
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
	
	public String getName() { return name; }
}
