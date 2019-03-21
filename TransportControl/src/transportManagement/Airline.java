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
	public String toViewingString() {
		String str = name;
		
		if( !super.getTransits().isEmpty() ) {
			str += " with flights: ";
			
			for(int index = 0; index < super.getTransits().size(); index++ ) {
				if( index != 0 ) str += ", ";
				
				str += ((Transition) (super.getTransits().values().toArray()[index])).toViewingString();
			}
		}
		
		return str;
	}
	
	@Override
	public String toString() {
		if( !super.getTransits().isEmpty() ) return name + super.getTransits().values();
		
		return name;
	}
}
