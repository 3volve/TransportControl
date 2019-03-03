package airport;

import java.util.*;

public class SystemManager
{	
	private Set<Airport> airports; private Set<Airline> airlines;
	
	public SystemManager() {
		airports = new TreeSet<Airport>();
		airlines = new TreeSet<Airline>();
	}
	
	//Creates an airport object and links it to the SystemManager.  The airport will have a name (code) n; n must have exactly three characters.  No two airports can have the same name.
	public void createAirport( String n ) {	
		if( n.length() != 3 ) System.out.println("Airport was unable to be added: incorrect name input.");
		else if( !airports.add(new Airport(n)) ) System.out.println("Airport was unable to be added: already created.");
	}
	
	//Creates an airline object with name n and links it to the SystemManager.  An airline has a name that must have a length less than 6.  No two airlines can have the same name.
	public void createAirline( String n ) {
		if( n.length() < 1 || n.length() > 5 ) System.out.println("Airport was unable to be added: incorrect name input.");
		else if( !airlines.add(new Airline(n)) ) System.out.println("Airport was unable to be added: already created.");
		}
	
	//Creates a flight for an airline named aname, from an originating airport (orig) to a destination airport (dest) on a particular date.  The flight has an identifier (id).
	public Flight createFlight( String name, String orig, String dest, int year, int month, int day, String id ) {
		
		return new Flight(orig, dest, year, month, day, id);
	}
	
	//Creates a section, of class s, for a flight with identifier flID, associated with an airline, air.  The section will contain the input number of rows and columns.
	public FlightSection createSection( String air, String flID, int rows, int cols, SeatClass s ) {
		
		return new FlightSection(s, rows, cols);
	}
	
	//Finds all flights from airport orig to airport dest with seats that are not booked.
	public ArrayList<Flight> findAvailableFlights( String orig, String dest ) {
		
		return new ArrayList<Flight>();
	}
	
	//Books seat in given row and column in section s, on flight fl of airline air.
	public void bookSeat( String air, String fl, SeatClass s, int row, char col ) {
		
	}
	
	//Displays attribute values for all objects (e.g., airports, airplanes) in system. 
	public void displaySystemDetails() {
		
	}
	
	Set<Airport> getAirports() { return airports; }
	
	Set<Airline> getAirlines() { return airlines; }
	
}
