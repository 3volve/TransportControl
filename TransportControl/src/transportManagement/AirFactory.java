package transportManagement;

import transportManagement.supportClasses.MyDate;
import transportManagement.supportClasses.NullSection;
import transportManagement.supportClasses.NullTransition;
import transportManagement.supportClasses.SystemTester;

class AirFactory implements TransportFactory {

	static TransportLine createTransportLine( String name ) {
		int nameMinSize = 1, nameMaxSize = 5;
		
		if( name.length() < nameMinSize || nameMaxSize < name.length() )
			System.out.println("Airline " + name + " was unable to be created: incorrect airline naming syntax.");
		else return new Airline(name);
		
		return null;
	}

	static Transition createTransition( Transition.DataClass data ) {
		Transition flight = new Flight(data.orig, data.departDate, data.ID, data.dest.get(0));
		
		String BaseErrorStr = "Flight " + data.ID + " was unable to be created: "; 
		MyDate curDate = new MyDate(3, 17, 2019); int maxMonth = 12, maxDays = 31;
		boolean[] conditions = new boolean[] { data.orig.equals(data.dest.get(0)), data.departDate.compareTo(curDate) < 0,
				maxMonth < data.departDate.getMonth() || maxDays < data.departDate.getDay(), data.line.isDuplicate(flight) };
		
		if( SystemTester.systemTest(BaseErrorStr, conditions, "same airport at origin and destination.",  "cannot create flight in the past.",
														   "invalid days and/or months.", "duplicate error.") )
			return flight;
		
		return new NullTransition();
	}
	
	static TransportSection createSection( TransportSection.DataClass data ) {
		String BaseErrorStr = "FlightSection " + data.seatClass + " class, of flight " + data.transID + " for airline " + data.line.name + " was unable to be created: ";
		int maxRows = 100;
		
		boolean[] conditions = new boolean[] { data.number <= 0 || maxRows < data.number, !data.layout.equals("small") || !data.layout.equals("medium") || !data.layout.equals("wide"),
											  data.line.getTransits().get(data.transID).getSections().containsKey(data.seatClass) };
		
		if( SystemTester.systemTest(BaseErrorStr, conditions, "incorrect # of rows.", "incorrect given section layout.", "duplicate SeatClass."))
			return new AirSection(data.seatClass, data.number, data.layout);
			
		return new NullSection();
	}

}
