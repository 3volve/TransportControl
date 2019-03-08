package airport;

import java.util.*;

public class SystemManager
{	
	private HashMap<String, Airport> airports; private HashMap<String, Airline> airlines;
	
	public SystemManager() {
		airports = new HashMap<String, Airport>();
		airlines = new HashMap<String, Airline>();
	}
	
	//Creates an airport object and links it to the SystemManager.  The airport will have a name (code) n; n must have exactly three characters.  No two airports can have the same name.
	public void createAirport( String n ) {	
		if( testCreateAir("Airport", n, n.length() != 3,  airports.containsKey(n)) )
			airports.put(n, new Airport(n));
	}
	
	//Creates an airline object with name n and links it to the SystemManager.  An airline has a name that must have a length less than 6.  No two airlines can have the same name.
	public void createAirline( String n ) {
		if( testCreateAir("Airline", n, (n.length() < 1 || n.length() > 5),  airlines.containsKey(n)) )
			airlines.put(n, new Airline(n));
	}
	
	//Creates a flight for an airline named aName, from an originating airport (orig) to a destination airport (dest) on a particular date.  The flight has an identifier (id).
	public void createFlight( String aName, String orig, String dest, int year, int month, int day, String id ) {
		String errorStr = ("Flight " + id + " for airline " + aName + " was unable to be created: ");
		
		if( orig.equals(dest) ) errorStr += "same airport at origin and destination.";
		else if( !airlines.containsKey(aName) ) errorStr += "no airline of the name, " + aName;
		//else if( airlines.containsKey(aName) ) System.out.println(aName + " has been found to be contained.");
		else if( !airports.containsKey(orig) || !airports.containsKey(dest) ) errorStr += "invalid airport " + orig + " and/or " + dest;
		else if( year < 2019 || (year < 2019 && month < 6) ) errorStr += "cannot create flight in the past.";
		else if( !airlines.get(aName).addFlight(new Flight(orig, dest, year, month, day, id)) ) errorStr += "flight already created.";
		
		if( errorStr.length() > 60 )
			System.out.println(errorStr);
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
	
	boolean testCreateAir( String type, String n, boolean length, boolean contains ) {
		String errorStr = type + " " + n + " was unable to be added: ";
		
		if( length ) errorStr += "incorrect name input.";
		else if( contains )	errorStr += "already created.";
		else return true;
		
		System.out.println(errorStr);
		
		return false;
		
	}
	
	HashMap<String, Airport> getAirports() { return airports; }
	
	HashMap<String, Airline> getAirlines() { return airlines; }
	
}
