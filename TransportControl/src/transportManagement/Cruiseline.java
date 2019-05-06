package transportManagement;

import java.util.HashMap;
import java.util.HashSet;

class Cruiseline extends TransportLine {
	
	private HashMap<String, CruiseShip> ships;
	private HashMap<CruiseShip, HashSet<Transition>> bookings;

	Cruiseline(String n) { 
		super(n);
		ships = new HashMap<String, CruiseShip>();
	}

	boolean addShip( String name, TransportSection... sections ) {
		if( !ships.containsKey(name) ) {
			ships.put(name, new CruiseShip(name));
			ships.get(name).setCabins(sections);
			bookings.put(ships.get(name), new HashSet<Transition>());
			return true;
		}
		
		return ships.get(name).setCabins(sections);
	}
	
	HashMap<String, CruiseShip> getShips() { return ships; }
	
	boolean addBooking( Transition transit, CruiseShip ship ) {
		if( !super.getTransits().containsKey(transit.ID) )
			if( bookings.containsKey(ship) ) {
				bookings.get(ship).add(transit);
				return true;
			}
			
		return false;
	}

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
			str += ", with Ships:\n";

			for( CruiseShip ship : ships.values() ) {
				str += " " + ship.getName();

				if( !bookings.get(ship).isEmpty() ) {
					str += ", currently booked for the trips:\n ";
					
					for( Transition transit : bookings.get(ship) )
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
