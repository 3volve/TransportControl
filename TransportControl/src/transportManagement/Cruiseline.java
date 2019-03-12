package transportManagement;

public class Cruiseline extends TransportLine {

	Cruiseline(String n) { super(n); }

	@Override
	boolean addTransit(Transition trans) {
		return false;
	}

	protected boolean hasTransit(String orig, String dest) {
		return false;
	}

}
