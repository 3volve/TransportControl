package transportManagement;

class Cruiseline extends TransportLine {
	
	HashMap<String, Trip> trips;
	HashMap<String, Ship> ships;

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
