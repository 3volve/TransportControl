package transportManagement;

import java.util.HashMap;

public class AirFactory implements TransportFactory {

	public TransportLine createTransportLine( String name, HashMap<String, TransportLine> airlines ) {
		String errorStr = "Airline " + name + " was unable to be created: "; int nameMinSize = 1, nameMaxSize = 5;
		boolean[] conditions = new boolean[] {airlines.containsKey(name), name.length() < nameMinSize || nameMaxSize < name.length()};
		
		if( SystemCreateTester.systemCreateTest(errorStr, conditions, "duplicate name.", "incorrect airlines naming syntax.") )
			return new Airline( name );
		
		return null;
	}

	public Transition createTransition( String lName, String orig, String dest, int year, int month, int day, String id,  HashMap<String, TransportLine> airlines, HashMap<String, City> cities ) {
		String BaseErrorStr = "Flight " + id + " for airline " + lName + " was unable to be created: "; 	int curYear = 2019, curMonth = 3, maxMonth = 12, maxDays = 31;
		Transition flight = new Flight(orig, dest, year, month, day, id);
		boolean[] conditions = new boolean[] {orig.equals(dest), !airlines.containsKey(lName), !cities.containsKey(orig) || !cities.containsKey(dest), 
											  year < curYear || (year < curYear && month < curMonth), maxMonth < month || maxDays < day, airlines.get(lName).isDuplicate(flight)};
		
		if( SystemCreateTester.systemCreateTest(BaseErrorStr, conditions, "same airport at origin and destination.", "no airline named " + lName + ".",
														   "invalid airport " + orig + " and/or " + dest + ".", "cannot create flight in the past.",
														   "invalid days and/or months", "duplicate error.") )
			return new Flight( orig, dest, year, month, day, id );
		
		return null;
	}

}
