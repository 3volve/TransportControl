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
		String errorStr = "Airport " + n + " was unable to be created: ";
		boolean[] conditions = new boolean[] {airports.containsKey(n), n.length() != 3};
		
		if( SystemCreateTester.systemCreateTest(errorStr, conditions,	"duplicate error", "incorrect airport naming syntax" ) )
			airports.put(n, new Airport(n));
	}
	
	//Creates an airline object with name n and links it to the SystemManager.  An airline has a name that must have a length less than 6.  No two airlines can have the same name.
	public void createAirline( String n ) {
		String errorStr = "Airline " + n + " was unable to be created: ";
		boolean[] conditions = new boolean[] {airlines.containsKey(n), (n.length() < 1 || n.length() > 5)};
		
		if( SystemCreateTester.systemCreateTest(errorStr, conditions, "duplicate error.", "incorrect airlines naming syntax.") )
			airlines.put(n, new Airline(n));
	}
	
	//Creates a flight for an airline named aName, from an originating airport (orig) to a destination airport (dest) on a particular date.  The flight has an identifier (id).
	public void createFlight( String aName, String orig, String dest, int year, int month, int day, String id ) {
		String errorStr = "Flight " + id + " for airline " + aName + " was unable to be created: ";
		boolean[] conditions = new boolean[] {orig.equals(dest), !airlines.containsKey(aName), !airports.containsKey(orig) || !airports.containsKey(dest), 
											  year < 2019 || (year < 2019 && month < 6)};
		
		if( SystemCreateTester.systemCreateTest(errorStr, conditions, "same airport at origin and destination.", "no such airline " + aName + ".",
														   "invalid airport " + orig + " and/or " + dest + ".", "cannot create flight in the past.") )
			if( !airlines.get(aName).addFlight(new Flight(orig, dest, year, month, day, id)) )
				System.out.println(errorStr + "duplicate error.");
	}
	
	//Creates a section, of class s, for a flight with identifier flID, associated with an airline, air.  The section will contain the input number of rows and columns.
	public void createSection( String air, String flID, int rows, int cols, SeatClass s ) {
		String errorStr = "Flight " + flID + " for airline " + air + " was unable to create new " + s + ": ";
		boolean[] conditions = new boolean[] {};
		
		airlines.get(air).getFlights().get(flID).addFlightSection(new FlightSection(s, rows, cols));
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
}
