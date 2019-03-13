package transportManagement;

public class Airline extends TransportLine
{
	Airline( String n ) { super(n); }
	
	@Override
	boolean hasTransit( String orig, String dest ) {
		for( Transition fly : transits.values() )
			if( fly.hasCities(orig, dest) ) return true;
		
		return false;
	}
	
	@Override
	public String toString() {
		if( !transits.isEmpty() ) return name + " with flights: " + transits.values();
		
		return name;
	}
}
