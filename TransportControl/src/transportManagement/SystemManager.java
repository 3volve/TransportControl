package transportManagement;

import java.util.*;

public class SystemManager
{	
	private HashMap<String, City> cities; private HashMap<String, TransportLine> lines;
	
	public SystemManager() {
		cities = new HashMap<String, City>();
		lines = new HashMap<String, TransportLine>();
	}
	
	//Creates an airport object and links it to the SystemManager.  The airport will have a name (code) n; n must have exactly three characters.  No two airports can have the same name.
	public void initializeCity( String name ) {	
		String BaseErrorStr = "City " + name + " was unable to be initialized: "; int nameSize = 3;
		boolean[] conditions = new boolean[] {cities.containsKey(name), name.length() != nameSize};
		
		if( SystemCreateTester.systemCreateTest(BaseErrorStr, conditions,	"duplicate name.", "incorrect airport naming syntax." ) )
			cities.put(name, new City(name));
	}
	
	//Creates an airline object with name n and links it to the SystemManager.  An airline has a name that must have a length less than 6.  No two airlines can have the same name.
	public void createTransportLine( String type, String name ) {
		String errorStr = type + " " + name + " was unable to be created: "; int nameMinSize = 1, nameMaxSize = 5;
		boolean[] conditions = new boolean[] {lines.containsKey(name), name.length() < nameMinSize || nameMaxSize < name.length()};
		TransportLineFactory lineFactory = new TransportLineFactory();
		
		if( SystemCreateTester.systemCreateTest(errorStr, conditions, "duplicate name.", "incorrect airlines naming syntax.") )
			lines.put(name, lineFactory.createTransportLine(type, name));
	}
	
	//Creates a flight for an airline named aName, from an originating airport (orig) to a destination airport (dest) on a particular date.  The flight has an identifier (id).
	public void createFlight( String aName, String orig, String dest, int year, int month, int day, String id ) {
		String BaseErrorStr = "Flight " + id + " for airline " + aName + " was unable to be created: "; 	int curYear = 2019, curMonth = 3, maxMonth = 12, maxDays = 31;
		boolean[] conditions = new boolean[] {orig.equals(dest), !lines.containsKey(aName), !cities.containsKey(orig) || !cities.containsKey(dest), 
											  year < curYear || (year < curYear && month < curMonth), maxMonth < month || maxDays < day};
		
		if( SystemCreateTester.systemCreateTest(BaseErrorStr, conditions, "same airport at origin and destination.", "no airline named " + aName + ".",
														   "invalid airport " + orig + " and/or " + dest + ".", "cannot create flight in the past.",
														   "invalid days and/or months") )
			if( !lines.get(aName).addTransit(new Flight(orig, dest, year, month, day, id)) )
				System.out.println(BaseErrorStr + "duplicate error.");
	}
	
	//Creates a section, of class s, for a flight with identifier flID, associated with an airline, air.  The section will contain the input number of rows and columns.
	public void createSection( String air, String flID, int rows, int cols, SeatClass s ) {
		String BaseErrorStr = "FlightSection " + s + " class, of flight " + flID + " for airline " + air + " was unable to be created: ";
		int maxRows = 100, maxCols = 10;
		boolean[] conditions = new boolean[] { rows <= 0 || cols <= 0 || maxRows < rows || maxCols < cols, !lines.containsKey(air), false };
		if( !conditions[1] ) conditions[2] = lines.get(air).getTransits().get(flID).getFlightSections().containsKey(s);
		
		if( SystemCreateTester.systemCreateTest(BaseErrorStr, conditions, "incorrect # of rows.", " no airline named " + air + ".", "duplicate SeatClass."))
			lines.get(air).getFlights().get(flID).addFlightSection(new FlightSection(s, rows, cols));
	}
	
	//Finds all flights from airport orig to airport dest with seats that are not booked.
	public void findAvailableFlights( String orig, String dest ) {
		ArrayList<Flight> availFlights = new ArrayList<Flight>();
		
		for( Airline air : airlines.values() ) {
			if( air.hasFlight(orig, dest) ) {
				for( Flight fly : air.getFlights().values() )
					if( fly.origAndDestMatch(orig, dest) )
						availFlights.add(fly);
			}
		}
		
		if( availFlights.isEmpty() )
			System.out.println("No flights were found going from " + orig + " to " + dest + ".");
		
		System.out.println("\nAll available flights traveling from " + orig + " to " + dest + ": ");
		System.out.println(availFlights.toString());
	}
	
	//Books seat in given row and column in section s, on flight fl of airline air.
	public void bookSeat( String air, String fl, SeatClass s, int row, char col ) {
		String BaseErrorStr = "Seat in " + s + " class, of flight " + fl + " for airline " + air + " was unable to be created: ";

		boolean[] conditions = new boolean[] {!airlines.containsKey(air), false, false};
		if( !conditions[0] ) conditions[1] = !airlines.get(air).getFlights().containsKey(fl);
		if( !conditions[0] && !conditions[1] ) conditions[2] = airlines.get(air).getFlights().get(fl).getFlightSections().get(s).isSeatAvailable(row, col);
		
		if( SystemCreateTester.systemCreateTest(BaseErrorStr, conditions, "no airline named " + air + ".", "no flight with ID " + fl + ".",
																		  "Seat " + row + col + " is already booked.") )
			airlines.get(air).getFlights().get(fl).getFlightSections().get(s).bookSeat(row, col);
	}
	
	//Displays attribute values for all objects (e.g., airports, airplanes) in system. 
	public void displaySystemDetails() {
		System.out.println("\nAirports: \n" + airports.values().toString());
		System.out.println("\nAirlines: ");
		
		for( Airline airline : airlines.values() )
			System.out.println(airline.toString());
	}
}
