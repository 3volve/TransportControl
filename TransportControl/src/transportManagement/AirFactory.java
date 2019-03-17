package transportManagement;

import java.util.HashMap;

import transportManagement.supportClasses.MyDate;
import transportManagement.supportClasses.SystemTester;

class AirFactory implements TransportFactory {

	static TransportLine createTransportLine( String name, HashMap<String, TransportLine> airlines ) {
		String errorStr = "Airline " + name + " was unable to be created: "; int nameMinSize = 1, nameMaxSize = 5;
		boolean[] conditions = new boolean[] {airlines.containsKey(name), name.length() < nameMinSize || nameMaxSize < name.length()};
		
		if( SystemTester.systemTest(errorStr, conditions, "duplicate name.", "incorrect airlines naming syntax.") )
			return new Airline(name);
		
		return null;
	}

	static Transition createTransition( Transition.DataClass data ) {
		String BaseErrorStr = "Flight " + data.ID + " for airline " + data.lName + " was unable to be created: "; int maxMonth = 12, maxDays = 31;
		Transition flight = new Flight(data.orig, data.departDate, data.ID, data.dest.get(0));
		boolean[] conditions = new boolean[] { data.orig.equals(data.dest.get(0)), !data.lines.containsKey(data.lName),
				!data.cities.containsKey(data.orig) || !data.cities.containsKey(data.dest.get(0)), data.departDate.compareTo(new MyDate(3, 17, 2019)) < 0,
				maxMonth < data.departDate.getMonth() || maxDays < data.departDate.getDay(), data.lines.get(data.lName).isDuplicate(flight) };
		
		if( SystemTester.systemTest(BaseErrorStr, conditions, "same airport at origin and destination.", "no airline named " + data.lName + ".",
														   "invalid airport " + data.orig + " and/or " + data.dest + ".", "cannot create flight in the past.",
														   "invalid days and/or months", "duplicate error.") )
			return flight;
		
		return new NullTransition();
	}
	
	static TransportSection createSection( TransportSection.DataClass data ) {
		String BaseErrorStr = "FlightSection " + data.seatClass + " class, of flight " + data.flID + " for airline " + data.airline + " was unable to be created: ";
		int maxRows = 100, maxCols = 10;
		
		boolean[] conditions = new boolean[] { data.rows <= 0 || data.cols <= 0 || maxRows < data.rows || maxCols < data.cols, !data.lines.containsKey(data.airline), false };
		if( !conditions[1] ) conditions[2] = data.lines.get(data.airline).getTransits().get(data.flID).getSections().containsKey(data.seatClass);
		
		if( SystemTester.systemTest(BaseErrorStr, conditions, "incorrect # of rows.", " no airline named " + data.airline + ".", "duplicate SeatClass."))
			return new AirSection(data.seatClass, data.rows, data.cols);
			
		return new NullSection();
	}

}
