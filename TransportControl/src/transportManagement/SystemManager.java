package transportManagement;

import java.util.*;

import transportManagement.supportClasses.*;

public class SystemManager
{	
	private HashMap<String, HashMap<String, City>> cities; private HashMap<String, HashMap<String, TransportLine>> lines;
	
	public SystemManager() {
		cities = new HashMap<String, HashMap<String, City>>();
		cities.put("Air", new HashMap<String, City>());
		cities.put("Train", new HashMap<String, City>());
		cities.put("Cruise", new HashMap<String, City>());
		
		lines = new HashMap<String, HashMap<String, TransportLine>>();
		lines.put("Air", new HashMap<String, TransportLine>());
		lines.put("Train", new HashMap<String, TransportLine>());
		lines.put("Cruise", new HashMap<String, TransportLine>());
	}
	
	public void initializeCity( String type, String name ) {	
		String BaseErrorStr = type + " named " + name + " was unable to be initialized: "; int nameSize = 3;
		boolean[] conditions = new boolean[] {cities.get(type).containsKey(name), name.length() != nameSize};
		
		if( SystemTester.systemTest(BaseErrorStr, conditions,	"duplicate name.", "incorrect airport naming syntax." ) )
			cities.get(type).put(name, new City(name));
	}
	
	public void createTransportLine( String type, String name ) {
		if( lines.get(type).containsKey(name) ) System.out.println(type + "line " + name + " was unable to be created: duplicate name.");
		
		else {
			TransportLine line = TransportFactory.createTransportLine(type, name, lines.get(type));
		
			if( line != null ) lines.get(type).put(name, line);
		}
	}
	
	public void createTransit( String type, String lineName, String orig, int dYear, int dMonth, int dDay, int aYear, int aMonth, int aDay, String id, String... dest ) {
		if( SystemTester.systemTest(type + "transit " + id + " was unable to be created: ",
				new boolean[] {!lines.get(type).containsKey(lineName), !cities.get(type).containsKey(orig)},
				"no " + type + "line under the name: " + lineName + ".", "no " + type + "port under the name: " + orig + ".") )
		{
		
			Transition transit = new NullTransition();
			transit = TransportFactory.createTransition(type, transit.new DataClass(lines.get(type).get(lineName), orig, new MyDate(dDay, dMonth, dYear), new MyDate(aDay, aMonth, aYear), id,  dest));
		
			if( !transit.getClass().getSimpleName().startsWith("Null") ) lines.get(type).get(lineName).addTransit(transit);
		}
	}
	
	public void createSection( String type, String line, String ID, int rows, int cols, SeatClass s ) {
		TransportSection section = new NullSection();
		section = TransportFactory.createSection(type, section.new DataClass(line, ID, rows, cols, s, lines.get(type)));
		
		if( !section.getClass().getSimpleName().startsWith("Null") )
			lines.get(type).get(line).getTransits().get(ID).addSection(section);
	}
	
	public void findAvailableTransits( String type, String orig, String dest ) {
		ArrayList<Transition> availTransits = new ArrayList<Transition>();
		
		for( TransportLine line : lines.get(type).values() ) {
			if( line.hasTransit(orig, dest) ) {
				for( Transition transit : line.getTransits().values() )
					availTransits.add(transit);
			}
		}
		
		if( availTransits.isEmpty() )
			System.out.println("No transits were found going from " + orig + " to " + dest + ".");
		
		System.out.println("\nAll available transits traveling from " + orig + " to " + dest + ": ");
		System.out.println(availTransits.toString());
	}
	
	public void bookSeat( String type, String line, String transit, SeatClass s, int row, char col ) {
		String BaseErrorStr = "Seat in " + s + " class, of transit " + transit + " for line " + line + " was unable to be created: ";

		boolean[] conditions = new boolean[] {!lines.get(type).containsKey(line), false, false};
		if( !conditions[0] ) conditions[1] = !lines.get(type).get(line).getTransits().containsKey(transit);
		if( !conditions[0] && !conditions[1] ) conditions[2] = lines.get(type).get(line).getTransits().get(transit).getSections().get(s).isSeatAvailable(row, col);
		
		if( SystemTester.systemTest(BaseErrorStr, conditions, "no airline named " + line + ".", "no flight with ID " + transit + ".",
																		  "Seat " + row + col + " is already booked.") )
			lines.get(type).get(line).getTransits().get(transit).getSections().get(s).bookSeat(row, col);
	}
	
	public void displaySystemDetails() {
		String[] portType = new String[] {"Airports", "Cruiseports", "Trainports"}, lineType = new String[] {"Airlines", "Cruiselines", "Trainlines"};
		
		for( int index = 0; index < portType.length; index++ ) {
			System.out.println(portType[index] + ": " + cities.get(portType[index]).values().toString());
			System.out.println("\n" + lineType[index] + ": ");
		
			for( TransportLine line : lines.get(lineType[index]).values() )
			System.out.println(line.toString());
		}
	}
}
