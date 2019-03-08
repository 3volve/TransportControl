package airport;

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
}
