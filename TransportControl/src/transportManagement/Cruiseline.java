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

	@Override
	public String toViewingString() {
		String str =  "Cruiseline: " + name;
		
		if( !super.getTransits().isEmpty() ) {
			str = str + " with Cruisetrips:";
			
			for( Transition transit : super.getTransits().values() )
				str += " " + transit.toViewingString();
		}
			
		if( !ships.isEmpty() ) {
			str = str + ", and Ships:" + ships.values();

			for( CruiseShip ship : ships.values() )
				str += " " + ship.toViewingString();
		}
		
		return str;
	}

	@Override
	public String toString() {
		String str = name;
		
		if( !super.getTransits().isEmpty() ) str = str + super.getTransits().values();
		if( !ships.isEmpty() ) str = str + ", " + ships.values();
		
		return str;
	}

}
