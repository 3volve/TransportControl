package transportManagement;

import transportManagement.supportClasses.MyDate;
import transportManagement.supportClasses.SystemTester;

class CruiseFactory implements TransportFactory {

	static TransportLine createTransportLine( String... name ) {
		int nameMinSize = 1, nameMaxSize = 5;
		if( name[0].length() < nameMinSize || nameMaxSize < name[0].length() )
			System.out.println("Cruiseline " + name + " was unable to be created: incorrect cruiseline naming syntax.");
		else {
			Cruiseline newCruise = new Cruiseline(name[0]);
			
			for( int index = 1; index < name.length; index++ )
				newCruise.addShip(name[index]);
		}
		
		return null;
	}

	static Transition createTransition( Transition.DataClass data ) {
		Transition trip = new CruiseTrip(data.orig, data.ID, data.departDate, data.arriveDate, data.dest);
		
		String BaseErrorStr = "Trip " + data.ID + " was unable to be created: ";
		MyDate curDate = new MyDate(3, 7, 2019); int maxMonth = 12, maxDays = 31;
		boolean[] conditions = new boolean[] { data.departDate.dayDifference(data.arriveDate) > 21, data.departDate.compareTo(curDate) < 0,
				maxMonth < data.departDate.getMonth() || maxDays < data.departDate.getDay(), data.line.isDuplicate(trip) };
		
		if( SystemTester.systemTest(BaseErrorStr, conditions, "trip cannot last longer than 21 days.", "cannot create trip in the past.",
														   "invalid days and/or months", "duplicate error.") )
			return trip;
		
		return new NullTransition();
	}
	
	static TransportSection createSection( TransportSection.DataClass data ) {
		
		
		return new NullSection();
	}

}
