package transportManagement;

public class Airline extends TransportLine
{
	Airline( String n ) { super(n); }
	
	boolean addTransit( Transition flight ) {
		return transits.putIfAbsent(flight.getID(), flight) == null;
	}
	
	boolean hasFlight( String orig, String dest ) {
		for( Transition fly : transits.values() )
			if( fly.origAndDestMatch(orig, dest) ) return true;
		
		return false;
	}
	
	@Override
	public String toString() {
		if( !transits.isEmpty() ) return name + " with flights: " + transits.values();
		return name;
	}
}
