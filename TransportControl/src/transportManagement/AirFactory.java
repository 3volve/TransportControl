package transportManagement;

import java.util.HashMap;

class AirFactory implements TransportFactory {

	static TransportLine createTransportLine( String name, HashMap<String, TransportLine> airlines ) {
		String errorStr = "Airline " + name + " was unable to be created: "; int nameMinSize = 1, nameMaxSize = 5;
		boolean[] conditions = new boolean[] {airlines.containsKey(name), name.length() < nameMinSize || nameMaxSize < name.length()};
		
		if( SystemCreateTester.systemCreateTest(errorStr, conditions, "duplicate name.", "incorrect airlines naming syntax.") )
			return new Airline( name );
		
		return null;
	}

	static Transition createTransition( TransitionDataClass data ) {
		String BaseErrorStr = "Flight " + data.ID + " for airline " + data.lName + " was unable to be created: "; 	int curYear = 2019, curMonth = 3, maxMonth = 12, maxDays = 31;
		Transition flight = new Flight(data.orig, data.year, data.month, data.day, data.ID, data.dest[0]);
		boolean[] conditions = new boolean[] { data.orig.equals(data.dest), !data.lines.containsKey(data.lName),
				!data.cities.containsKey(data.orig) || !data.cities.containsKey(data.dest), data.year < curYear || (data.year < curYear && data.month < curMonth),
				maxMonth < data.month || maxDays < data.day, data.lines.get(data.lName).isDuplicate(flight) };
		
		if( SystemCreateTester.systemCreateTest(BaseErrorStr, conditions, "same airport at origin and destination.", "no airline named " + data.lName + ".",
														   "invalid airport " + data.orig + " and/or " + data.dest + ".", "cannot create flight in the past.",
														   "invalid days and/or months", "duplicate error.") )
			return new Flight( data.orig, data.year, data.month, data.day, data.ID, data.dest[0] );
		
		return null;
	}

}
