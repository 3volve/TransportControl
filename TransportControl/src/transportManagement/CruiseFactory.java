package transportManagement;

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
			
			if( ships != null )
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
		Transition trip = new NullTransition();
		CruiseShip shipToBook = null;
		
		for( CruiseShip ship : cruiseline.getShips().values() ) {
			if( ship.isAvailable(data.departDate, data.arriveDate) ) {
				shipToBook = ship;
				break;
			}
		}
		
		String baseErrorStr = "Trip " + data.ID + " was unable to be created: ";
		boolean[] conditions = new boolean[] {
				data.departDate.dayDifference(data.arriveDate) > 21,	
				data.departDate.compareToPresent() < 0 && data.departDate.compareTo(data.arriveDate) > 0,
				!data.departDate.isValid() || !data.arriveDate.isValid(),	
				data.line.isDuplicate(trip),				
				shipToBook == null	
				};
		
		if( SystemTester.systemTest (
				baseErrorStr, conditions,					
				"trip cannot last longer than 21 days.",	
				"cannot create trip in and/or to the past.",		
				"invalid days and/or months",			
				"duplicate error.",		
				"no available ship from cruiseline " + data.line.name + " between set dates.")
				) {
			shipToBook.bookShip(data.departDate, data.arriveDate);
			return trip;
		}
		
		return new NullTransition();
	}
	
	//ship can't be null, line has already been checked, ship doesn't already have a transportClass given
	static TransportSection createSection( TransportSection.DataClass data ) {
		Cruiseline cruiseline = (Cruiseline)data.line;
		CruiseShip ship = cruiseline.getShips().get(data.transID);
		TransportSection cabin = new CruiseSection(data.number, data.layout, data.seatClass, data.pricing);
		
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
