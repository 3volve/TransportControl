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
	
	public void createPort( String type, String name ) {	
		String BaseErrorStr = type + " named " + name + " was unable to be initialized: "; int nameSize = 3;
		boolean[] conditions = new boolean[] {cities.get(type).containsKey(name), name.length() != nameSize};
		
		if( SystemTester.systemTest(BaseErrorStr, conditions, "duplicate name.", "incorrect airport naming syntax." ) )
			cities.get(type).put(name, new City(name));
	}
	
	public void createTransportLine( String type, String name, String... fleet ) {
		if( lines.get(type).containsKey(name) ) System.out.println(type + "line " + name + " was unable to be created: duplicate name.");
		
		else {
			TransportLine line = TransportFactory.createTransportLine(type, name, fleet);
		
			if( line != null ) lines.get(type).put(name, line);
		}
	}
	
	public void createTransit( String type, String lineName, String orig, MyDate depart, MyDate arrive, String id, String... dest ) {
		if( SystemTester.systemTest(type + "transit " + id + " was unable to be created: ",
				new boolean[] {!lines.get(type).containsKey(lineName), !cities.get(type).containsKey(orig)},
				"no " + type + "line under the name: " + lineName + ".", "no " + type + "port under the name: " + orig + ".") )
		{
			Transition transit = new NullTransition();
			transit = TransportFactory.createTransition(type, transit.new DataClass(lines.get(type).get(lineName), orig, depart, arrive, id,  dest));
		
			if( !transit.getClass().getSimpleName().startsWith("Null") ) lines.get(type).get(lineName).addTransit(transit);
		}
	}
	
	public void createSection( String type, String line, String ID, int rows, String layoutName, SeatClass s ) {
		if( !lines.get(type).containsKey(line) )
			System.out.println(type + "section " + ID + " was unable to be created: no " + type + "line under the name: " + line + ".");
		else {
			TransportSection section = new NullSection();
			section = TransportFactory.createSection(type, section.new DataClass(ID, rows, layoutName, s, lines.get(type).get(line)));
		
			if( !section.getClass().getSimpleName().startsWith("Null") )
				lines.get(type).get(line).getTransits().get(ID).addSection(section);
		}
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
	
	public void displaySystemDetails( String type ) {
		if( cities.get(type) != null  || cities.get(type).isEmpty() )
			System.out.println(type + "ports: " + cities.get(type).values().toString());
		else System.out.println("No " + type + "ports have been initialized yet.");
		
		if( lines.get(type) != null  || lines.get(type).isEmpty() ) {
			System.out.println("\n" + type + "lines: ");
		
			for( TransportLine line : lines.get(type).values() )
					System.out.println(line.toString());
		}
		else System.out.println("No " + type + "lines have been initialized yet.");
	}
	
	public void displayAllSystemDetails() {
		String[] type = new String[] {"Air", "Cruise", "Train"};
		
		for( int index = 0; index < type.length; index++ ) {
			if( index != 0 ) System.out.println();
			
			displaySystemDetails(type[index]);
		}
	}
}
