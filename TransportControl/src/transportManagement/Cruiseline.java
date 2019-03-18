package transportManagement;

import java.util.HashMap;

class Cruiseline extends TransportLine {
	
	private HashMap<String, CruiseShip> ships;

	Cruiseline(String n) { super(n); }

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

}
