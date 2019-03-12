package transportManagement;

import java.util.HashMap;

public class Trainline extends TransportLine
{
	Trainline( String n ) { super(n); }

	@Override
	boolean addTransit(Transition trans) {
		return false;
	}

	@Override
	boolean hasTransit(String orig, String dest) {
		for( Transition fly : transits.values() )
			if( fly.hasCities(orig, dest) ) return true;
		
		return false;
	}
}
