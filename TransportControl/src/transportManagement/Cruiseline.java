package transportManagement;

import java.util.HashMap;
import java.util.HashSet;

class Cruiseline extends TransportLine {
	
	private HashMap<String, CruiseShip> ships;
	private HashMap<CruiseShip, HashSet<CruiseTrip>> bookings;
	//I need to set up some way to connect a ship to all of it's trips.
	//alternatively I could also have each trip have it's ship as a field
	Cruiseline(String n) { 
		super(n);
		ships = new HashMap<String, CruiseShip>();
	}

	boolean addShip( String name, TransportSection... sections ) {
		if( !ships.containsKey(name) ) {
			ships.put(name, new CruiseShip(name));
			ships.get(name).setCabins(sections);
			return true;
		}
		
		return ships.get(name).setCabins(sections);
	}
	
	HashMap<String, CruiseShip> getShips() { return ships; }

	@Override
	boolean hasTransit(String orig, String... dest) {
		for( Transition trip : super.getTransits().values() )
			if( trip.hasCities(orig, dest) ) 
				return true;
		
		return false;
	}

	@Override
	String toViewingString() {
		String str =  "Cruiseline: " + name;
		
		if( !ships.isEmpty() ) {
			str += ", with Ships:\n	  ";

			for( CruiseShip ship : ships.values() ) {
				str += " " + ship.getName();
			
				if( super.getTransits().containsKey(ship.getName()) ) {
					str += ", currently booked for the trips:";
				
					for( Transition transit : super.getTransits().values() )
						str += " " + transit.toViewingString();
				}
				else {
					str += ", currently without any trips.\n   ";
				}
			}
		}
		
		return str;
	}
}
