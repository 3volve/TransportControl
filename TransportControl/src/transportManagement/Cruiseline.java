package transportManagement;

import java.util.HashMap;

class Cruiseline extends TransportLine {
	
	HashMap<String, CruiseTrip> trips;
	HashMap<String, CruiseShip> ships;

	Cruiseline(String n) { super(n); }

	@Override
	boolean addTransit(Transition trans) {
		return false;
	}

	@Override
	boolean hasTransit(String orig, String... dest) {
		// TODO Auto-generated method stub
		return false;
	}

}
