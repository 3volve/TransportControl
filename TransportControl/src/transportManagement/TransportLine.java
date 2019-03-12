package transportManagement;

import java.util.HashMap;

public abstract class TransportLine {

	protected String name;
	protected HashMap<String, Transition> transits;
	
	TransportLine( String n ) { name = n; transits = new HashMap<String, Transition>(); }
	
	abstract boolean addTransit( Transition trans );

	HashMap<String, Transition> getTransits() { return transits; }

	abstract boolean hasTransit(String orig, String dest);
}
