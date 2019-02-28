package airport;

import java.util.ArrayList;

public class Airline
{
	private String name;
	private ArrayList<Flight> flights;
	
	Airline( String n ) { name = n; flights = new ArrayList<Flight>(); }
	
	void addFlight( Flight flight ) {
		if( !flights.contains(flight) ) flights.add(flight);
		
		else System.out.print("Flight already in system for the airline " + name);
	}
}
