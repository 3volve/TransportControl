package airport;

import java.util.ArrayList;

public class SystemManager
{	
	private ArrayList<Airport> airports; private ArrayList<Airline> airlines;
	
	public SystemManager() {
		airports = new ArrayList<Airport>();
		airlines = new ArrayList<Airline>();
	}
	
	//Creates an airport object and links it to the SystemManager.  The airport will have a name (code) n; n must have exactly three characters.  No two airports can have the same name.
	public void createAirport( String n ) {	
		TransportHubFactory transHubFactory = new TransportHubFactory();
		Airport newAir;
		
		if( !arrayContains(new Airport(n), airports) ) {
			newAir = transHubFactory.createTransportHub(n);
			airports.add(newAir);
		}
		else
			System.out.println("Airport already created.");
	}
	
	//Creates an airline object with name n and links it to the SystemManager.  An airline has a name that must have a length less than 6.  No two airlines can have the same name.
	public void createAirline( String n ) {
		
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
	
	private boolean arrayContains( Object obj, ArrayList arr ) {
		for( Object o : arr ) 
			if( o.toString().equals(obj.toString()) ) return true;
		
		return false;
	}
}
