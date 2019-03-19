package transportManagement;

import transportManagement.supportClasses.MyDate;
import transportManagement.supportClasses.NullSection;
import transportManagement.supportClasses.NullTransition;
import transportManagement.supportClasses.SystemTester;

class CruiseFactory implements TransportFactory {

	static TransportLine createTransportLine( String name, String... ships ) {
		int nameMinSize = 1, nameMaxSize = 5;
		if( name.length() < nameMinSize || nameMaxSize < name.length() )
			System.out.println("Cruiseline " + name + " was unable to be created: incorrect cruiseline naming syntax.");
		else {
			Cruiseline newCruise = new Cruiseline(name);
			
			for( int index = 0; index < ships.length; index++ )
				newCruise.addShip(ships[index]);
			
			return newCruise;
		}
		
		return null;
	}

	//checks if available ship from the cruiseline in question, checks if the trip length is greater than 21 (using a rough estimate of each month being 30 days)
	//checks if the departure date is earlier than the present or is after the arrive date, checks if the months and 
	static Transition createTransition( Transition.DataClass data ) {
		Cruiseline cruiseline = (Cruiseline)data.line;
		Transition trip = new NullTransition(); boolean hasBookedShip = false;
		
		for( CruiseShip ship : cruiseline.getShips().values() ) {
			if( hasBookedShip ) break;
			
			if( ship.bookShip(data.departDate, data.arriveDate) ) {
				trip = new CruiseTrip(data.orig, data.ID, data.departDate, data.arriveDate, ship, data.dest);
				hasBookedShip = true;
			}
		}
		
		MyDate curDate = new MyDate(3, 18, 2019); //MyDate is (Month, Day, Year) and yeah probably better ways of doing this if I don't get around to implementing anything better
		String baseErrorStr = "Trip " + data.ID + " was unable to be created: ";
		boolean[] conditions = new boolean[] {
				data.departDate.dayDifference(data.arriveDate) > 21,						//boolean #1
				data.departDate.compareTo(curDate) < 0 && data.departDate.compareTo(data.arriveDate) > 0,	//2
				MyDate.isValid(data.departDate) || MyDate.isValid(data.arriveDate),			//3
				data.line.isDuplicate(trip),												//4
				!hasBookedShip																//5
				};
		
		if( SystemTester.systemTest (
				baseErrorStr, conditions,					
				"trip cannot last longer than 21 days.",					//1
				"cannot create trip in and/or to the past.",					//2
				"invalid days and/or months",								//3
				"duplicate error.",											//4
				"no available ship from cruiseline " + data.line.name + " between set dates.")	//5
				)
			return trip;
		
		return new NullTransition();
	}
	
	//ship can't be null, line has already been checked, ship doesn't already have a transportClass given
	static TransportSection createSection( TransportSection.DataClass data ) {
		Cruiseline cruiseline = (Cruiseline)data.line;
		CruiseShip ship = cruiseline.getShips().get(data.transID);
		TransportSection cabin = new CruiseSection(data.number, data.layout, data.seatClass);
		
		String baseErrorStr = "Cabin " + data.seatClass + " was unable to be created: ";
		boolean[] conditions = new boolean[] { ship == null, !ship.setCabin(cabin) };
		
		SystemTester.systemTest (
				baseErrorStr, conditions,
				"no ship by the name of " + data.transID + ".",
				"seatingClass " + cabin.getTransportClass() + " has already been created for given ship."
				);
		
		return new NullSection();
	}

}
