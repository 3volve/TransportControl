package transportManagement;

import java.util.HashMap;
import java.util.HashSet;

import transportManagement.supportClasses.MyDate;
import transportManagement.supportClasses.TransportClass;

public class CruiseShip {
	private HashSet<CruiseTrip> trips;
	private HashMap<TransportClass, TransportSection> cabins;
	private String name;
	
	CruiseShip( String n ) {
		trips = new HashSet<CruiseTrip>();
		cabins = new HashMap<TransportClass, TransportSection>();
		name = n;
	}
	
	void bookShip( CruiseTrip newBooking ) {
		MyDate depart = newBooking.departDate, arrive = newBooking.getArriveDate();
		
		if( !trips.isEmpty() ) {
			for( CruiseTrip booking : trips ) {
				MyDate bookingDep = booking.departDate, bookingArr = booking.getArriveDate();
				
				if( (depart.isBetween(bookingDep, bookingArr) || arrive.isBetween(bookingDep, bookingArr)) ||
					(depart.compareTo(bookingDep) < 0 && arrive.compareTo(bookingArr) > 0) )
						return;
			}
		}
		
		trips.add(newBooking);
	}
	
	boolean isAvailable( MyDate depart, MyDate arrive ) {
		if( !trips.isEmpty() )
			for( CruiseTrip booking : trips ) {
				MyDate bookingDep = booking.departDate, bookingArr = booking.getArriveDate();
			
			if( (depart.isBetween(bookingDep, bookingArr) || arrive.isBetween(bookingDep, bookingArr)) ||
				(depart.compareTo(bookingDep) < 0 && arrive.compareTo(bookingArr) > 0) )
					return false;
			}
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
		
		if( !trips.isEmpty() )
			for( CruiseTrip trip : trips )
				trip.addSection(section);
		
		return true;
	}
	
	boolean hasTrips() { return !trips.isEmpty(); }
	
	HashSet<CruiseTrip> getTrips() { return trips; }
	
	HashMap<TransportClass, TransportSection> getLayout() { 
		HashMap<TransportClass, TransportSection> cloned = new HashMap<TransportClass, TransportSection>();
		
		for( TransportClass cabin : cabins.keySet() )
			cloned.put(cabin, ((CruiseSection)cabins.get(cabin)).clone());
		
		return cloned;
	}
	
	public String getName() { return name; }
}
