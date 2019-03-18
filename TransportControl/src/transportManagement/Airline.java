package transportManagement;

class Airline extends TransportLine
{
	Airline( String n ) { super(n); }
	
	@Override
	boolean hasTransit( String orig, String... dest ) {
		for( Transition fly : super.getTransits().values() )
			if( fly.hasCities(orig, dest[0]) ) return true;
		
		return false;
	}
	
	@Override
	public String toString() {
		if( !super.getTransits().isEmpty() ) return name + " with flights: " + super.getTransits().values();
		
		return name;
	}
}
