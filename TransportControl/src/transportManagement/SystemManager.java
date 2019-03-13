package transportManagement;

import java.util.*;

public class SystemManager
{	
	private HashMap<String, HashMap<String, City>> cities; private HashMap<String, HashMap<String, TransportLine>> lines;
	
	public SystemManager() {
		cities = new HashMap<String, HashMap<String, City>>();
		cities.put("Airline", new HashMap<String, City>());
		cities.put("Trainline", new HashMap<String, City>());
		cities.put("Cruiseline", new HashMap<String, City>());
		lines = new HashMap<String, HashMap<String, TransportLine>>();
		lines.put("Airline", new HashMap<String, TransportLine>());
		lines.put("Trainline", new HashMap<String, TransportLine>());
		lines.put("Cruiseline", new HashMap<String, TransportLine>());
	}
	
	public void initializeCity( String type, String name ) {	
		String BaseErrorStr = type + " named " + name + " was unable to be initialized: "; int nameSize = 3;
		boolean[] conditions = new boolean[] {cities.get(type).containsKey(name), name.length() != nameSize};
		
		if( SystemCreateTester.systemCreateTest(BaseErrorStr, conditions,	"duplicate name.", "incorrect airport naming syntax." ) )
			cities.get(type).put(name, new City(name));
	}
	
	public void createTransportLine( String type, String name ) {
		TransportLine line = TransportFactory.createTransportLine(type, name, lines.get(type));
		
		if( line != null ) lines.get(type).put(name, line);
	}
	
	public void createTransit( String type, String lineName, String orig, int year, int month, int day, String id, String... dest ) {
		Transition transit = new NullTransition();
		transit = TransportFactory.createTransition(type, transit.new DataClass(lineName, orig, year, month, day, id, lines.get(type), cities.get(type),  dest));
		
		if( transit.getClass().getSimpleName().startsWith("Null") ) lines.get(type).get(lineName).addTransit(transit);
	}
	
	public void createSection( String type, String line, String ID, int rows, int cols, SeatClass s ) {
		TransportSection section = new NullSection();
		section = TransportFactory.createSection(type, section.new DataClass(line, ID, rows, cols, s, lines.get(type)));
		
		if( !section.getClass().getSimpleName().startsWith("Null") )
			lines.get(type).get(line).getTransits().get(ID).addSection(new AirSection(s, rows, cols));
	}
	
	public void findAvailableTransits( String type, String orig, String dest ) {
		ArrayList<Transition> availFlights = new ArrayList<Transition>();
		
		for( TransportLine air : lines.get(type).values() ) {
			if( air.hasTransit(orig, dest) ) {
				for( Transition fly : air.getTransits().values() )
					availFlights.add(fly);
			}
		}
		
		if( availFlights.isEmpty() )
			System.out.println("No flights were found going from " + orig + " to " + dest + ".");
		
		System.out.println("\nAll available flights traveling from " + orig + " to " + dest + ": ");
		System.out.println(availFlights.toString());
	}
	
	public void bookSeat( String type, String line, String transit, SeatClass s, int row, char col ) {
		String BaseErrorStr = "Seat in " + s + " class, of flight " + transit + " for airline " + line + " was unable to be created: ";

		boolean[] conditions = new boolean[] {!lines.get(type).containsKey(line), false, false};
		if( !conditions[0] ) conditions[1] = !lines.get(type).get(line).getTransits().containsKey(transit);
		if( !conditions[0] && !conditions[1] ) conditions[2] = lines.get(type).get(line).getTransits().get(transit).getSections().get(s).isSeatAvailable(row, col);
		
		if( SystemCreateTester.systemCreateTest(BaseErrorStr, conditions, "no airline named " + line + ".", "no flight with ID " + transit + ".",
																		  "Seat " + row + col + " is already booked.") )
			lines.get(type).get(line).getTransits().get(transit).getSections().get(s).bookSeat(row, col);
	}
	
	public void displaySystemDetails( String type ) {
		System.out.println("\nAirports: \n" + cities.get(type).values().toString());
		System.out.println("\nAirlines: ");
		
		for( TransportLine line : lines.get(type).values() )
			System.out.println(line.toString());
	}
}
