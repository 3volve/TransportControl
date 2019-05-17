package transportManagement;

import java.util.HashMap;
import java.util.HashSet;

class Cruiseline extends TransportLine {
	
	private HashMap<String, CruiseShip> ships;

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
		String str =  "- " + name;
		
		if( !ships.isEmpty() ) {
			str += ", with Ships:";

			for( CruiseShip ship : ships.values() ) {
				str += "\n    " + ship.getName();

				if( ship.hasTrips() ) {
					str += ", currently booked for the trips:";
					
					for( CruiseTrip trip : ship.getTrips() )
						str += " " + trip.toViewingString();
				}
				else {
					str += ", currently without any trips.\n";
				}
			}
		}
		
		return str;
	}
}
