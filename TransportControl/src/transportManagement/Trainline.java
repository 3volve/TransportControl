package transportManagement;

class Trainline extends TransportLine
{
	Trainline( String n ) { super(n); }

	@Override
	boolean addTransit(Transition trans) {
		return false;
	}

	@Override
	boolean hasTransit(String orig, String... dest) {
		for( Transition transit : transits.values() )
			if( transit.hasCities(orig, dest) ) return true;
		
		return false;
	}
}
