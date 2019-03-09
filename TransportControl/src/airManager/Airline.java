package airManager;

import java.util.HashMap;
public class Airline
{
	private String name;
	private HashMap<String, Flight> flights;
	
	Airline( String n ) { name = n; flights = new HashMap<String, Flight>(); }
	
	boolean addFlight( Flight flight ) {
		return flights.putIfAbsent(flight.getID(), flight) == null;
	}
	
	String getName() { return name; }
	
	HashMap<String, Flight> getFlights() { return flights; }
	
	boolean hasFlight( String orig, String dest ) {
		for( Flight fly : flights.values() )
			if( fly.origAndDestMatch(orig, dest) ) return true;
		
		return false;
	}
	
	@Override
	public String toString() {
		if( !flights.isEmpty() ) return name + " with flights: " + flights.values();
		return name;
	}
}
