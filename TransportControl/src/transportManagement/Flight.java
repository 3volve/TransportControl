package transportManagement;

import java.util.HashMap;

import transportManagement.supportClasses.MyDate;
import transportManagement.supportClasses.TransportClass;

class Flight extends Transition
{
	private String dest;
	
	Flight( String orig, MyDate depart, String id, String de )
	{ super(orig, id, depart, new HashMap<TransportClass, TransportSection>()); }
	
	protected void addSection( TransportSection section ) { super.getSections().put(section.getTransportClass(), section); }

	protected boolean hasCities( String o, String... d ) {
		if( o.equals(origin) && d[0].equals(dest) ) return true;
		
		return false;
	}
	
	@Override
	public String toString() {
		return "Flight " + ID + " traveling from " + origin + " to " + dest + " on " + departDate.toString() +
			   " with Flight Sections:\n\t\t\t" + super.getSections().values().toString();
	}
}
