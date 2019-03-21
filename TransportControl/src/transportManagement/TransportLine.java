package transportManagement;

import java.util.HashMap;

abstract class TransportLine {

	final String name;
	private HashMap<String, Transition> transits;
	
	TransportLine( String n ) { name = n; transits = new HashMap<String, Transition>(); }
	
	boolean addTransit( Transition transit ) {
		return transits.putIfAbsent(transit.ID, transit) == null;
	}

	HashMap<String, Transition> getTransits() { return transits; }

	abstract boolean hasTransit(String orig, String... dest);

	final boolean isDuplicate(Transition transit) {
		if( transits.containsValue(transit) ) return true;
		
		return false;
	}
	
	abstract String toViewingString();
	
	public abstract String toString();
}
