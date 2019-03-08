package airport;

import java.util.HashSet;
public class Airline
{
	private String name;
	private HashSet<Flight> flights;
	
	Airline( String n ) { name = n; flights = new HashSet<Flight>(); }
	
	boolean addFlight( Flight flight ) {
		return flights.add(flight);
	}
	
	String getName() { return name; }
	
	HashSet<Flight> getFlights() { return flights; }
}
