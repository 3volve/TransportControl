package transportManagement;

import java.io.*;
import java.util.*;

import transportManagement.supportClasses.*;

public class SystemManager
{	
	private HashMap<String, HashMap<String, City>> cities; private HashMap<String, HashMap<String, TransportLine>> lines;
	
	public SystemManager() {
		cities = new HashMap<String, HashMap<String, City>>();
		cities.put("Air", new HashMap<String, City>());
		cities.put("Cruise", new HashMap<String, City>());
		
		lines = new HashMap<String, HashMap<String, TransportLine>>();
		lines.put("Air", new HashMap<String, TransportLine>());
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
	
	public void createSection( String type, String line, String ID, int rows, String layoutName, TransportClass seatClass, int pricing ) {
		if( !lines.get(type).containsKey(line) )
			System.out.println(type + "section " + ID + " was unable to be created: no " + type + "line under the name: " + line + ".");
		else {
			TransportSection section = new NullSection();
			section = TransportFactory.createSection(type, section.new DataClass(ID, rows, pricing, layoutName, seatClass, lines.get(type).get(line)));
		
			if( !section.getClass().getSimpleName().startsWith("Null") )
				lines.get(type).get(line).getTransits().get(ID).addSection(section);
		}
	}
	
	public void findAvailableTransits( String type, String orig, String dest, MyDate depart ) {
		ArrayList<Transition> availTransits = new ArrayList<Transition>();
		
		for( TransportLine line : lines.get(type).values() ) {
			if( line.hasTransit(orig, dest) ) {
				for( Transition transit : line.getTransits().values() ) {
					if( transit.hasCities(orig,  dest) && transit.departsOn(depart) )
						availTransits.add(transit);
				}
			}
		}
		
		if( !cities.get(type).containsKey(orig) || !cities.get(type).containsKey(dest) )
			System.out.println("One or both of the given cities do not exist.");
		else if( availTransits.isEmpty() )
			System.out.println("No transits were found going from " + orig + " to " + dest + ".");
		else {
			System.out.println("\nAll available transits traveling from " + orig + " to " + dest + ": ");
			
			for( Transition transit : availTransits)
				System.out.println(transit.toViewingString());
		}
	}
	
	public void bookSeat( String type, String line, String transit, TransportClass s, int row, char col ) {
		String BaseErrorStr = "Seat in " + s + " class, of transit " + transit + " for line " + line + " was unable to be created: ";

		boolean[] conditions = new boolean[] {!lines.get(type).containsKey(line), false, false};
		if( !conditions[0] ) conditions[1] = !lines.get(type).get(line).getTransits().containsKey(transit);
		if( !conditions[0] && !conditions[1] ) conditions[2] = !lines.get(type).get(line).getTransits().get(transit).getSections().get(s).isSeatAvailable(row, col);
		
		if( SystemTester.systemTest(BaseErrorStr, conditions, "no " + type + "line named " + line + ".", "no flight with ID " + transit + ".",
																		  "Seat " + row + col + " is already booked.") )
			lines.get(type).get(line).getTransits().get(transit).getSections().get(s).bookSeat(row, col);
	}
	
	public void bookAirplaneSeatByPreference( String type, String line, String transit, SeatClass s, String seatingPref ) {
		String BaseErrorStr = "Seat in " + s + " class, of transit " + transit + " for line " + line + " was unable to be created: ";

		boolean[] conditions = new boolean[] {!lines.get(type).containsKey(line), false, false, false};
		if( !conditions[0] ) conditions[1] = !lines.get(type).get(line).getTransits().containsKey(transit);
		if( !conditions[0] && !conditions[1] ) conditions[2] = !lines.get(type).get(line).getTransits().get(transit).getSections().containsKey(s);
		if( !conditions[0] && !conditions[1] && !conditions[2] ) conditions[3] = !((AirSection) lines.get(type).get(line).getTransits().get(transit).getSections().get(s)).isSeatPrefAvailable(seatingPref);
		
		if( SystemTester.systemTest(
				BaseErrorStr, conditions,
				"no airline named " + line + ".",
				"no flight with ID " + transit + ".",
				"no established " + s + " section.",
				"No available seats under seating preference."
			) ) 	((AirSection) lines.get(type).get(line).getTransits().get(transit).getSections().get(s)).bookSeatByPref(seatingPref); 
	}
	
	public void changePricing( String type, String line, String transitID, TransportClass section, int newPrice ) {
		if( lines.get(type).containsKey(line) ) {
			if( lines.get(type).get(line).getTransits().containsKey(transitID) ) {
				if( lines.get(type).get(line).getTransits().get(transitID).getSections().containsKey(section) ) {
					lines.get(type).get(line).getTransits().get(transitID).getSections().get(section).setPricing(newPrice);
					System.out.println("Pricing has been changed.");
				}
			}
		}
		
		else System.out.println("Unable to change pricing: invalid " + type + "line, " + type + "transit, or section");
	}
	
	public void changePricing( String type, String line, String origin, String destination, TransportClass section, int newPrice ) {
		if( lines.get(type).containsKey(line) ) {
			for( Transition transit : lines.get(type).get(line).getTransits().values() ) {
				if( transit.hasCities(origin,  destination) && transit.getSections().containsKey(section) ) {
					transit.getSections().get(section).setPricing(newPrice);
					System.out.println("Pricing has been changed.");
				}
			}
		}
		
		else System.out.println("Unable to change pricing: invalid " + type + "line, or section");
	}
	
	public void displaySystemDetails( String type ) {
		if( cities.get(type) != null  && !cities.get(type).isEmpty() )
			System.out.println("---" + type + "ports---\n" + cities.get(type).values().toString());
		else System.out.println("No " + type + "ports have been initialized yet.");
		
		if( lines.get(type) != null  && !lines.get(type).isEmpty() ) {
			System.out.println("\n---" + type + "lines---");
		
			for( TransportLine line : lines.get(type).values() )
					System.out.println(line.toViewingString());
		}
		else System.out.println("No " + type + "lines have been initialized yet.");
	}
	
	public void displayAllSystemDetails() {
		String[] type = new String[] {"Air", "Cruise"};
		
		for( int index = 0; index < type.length; index++ ) {
			if( index != 0 ) System.out.println();
			
			displaySystemDetails(type[index]);
		}
	}
	
	public void printAirsystemDetailsToFile( String fileName, Scanner keyboard ) {
		PrintStream fout = null;
		File file;
		
		do {
			file = new File(fileName);
			
			if( file.exists() ) {
				try { fout = new PrintStream(file); }
				catch( FileNotFoundException e ) { System.out.println(e); }
			}
			else {
				System.out.print("Given file does not exist, ");
				fileName = SystemMenu.strPrompt("new file", keyboard);
			}
			
		} while( !file.exists() );
		
		if( cities.get("Air") != null  || cities.get("Air").isEmpty() )
			fout.print(cities.get("Air").values().toString() + "{");
		
		if( lines.get("Air") != null  || lines.get("Air").isEmpty() ) {		
			Iterator<TransportLine> iter = lines.get("Air").values().iterator();
			
			for( int index = 0; iter.hasNext(); index++ ) {
				TransportLine line = iter.next();
				if( index != 1 ) fout.print(", ");
					fout.print(line.toString());
			}
		}
		
		fout.print("}");
		fout.close();
	}
	
	public String allPortsToString( String type ) {	return cities.get(type).values().toString(); }

	public String allLinesToString( String type ) {
		String str = "[";
		
		for( int index = 0; index < lines.get(type).size(); index++ ) {
			if( index != 0 ) str += ", ";
			
			str += ((TransportLine) lines.get(type).values().toArray()[index]).getName();
		}
		
		return str + "]";
	}
}
