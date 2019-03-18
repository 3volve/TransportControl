package transportManagement;

import java.util.HashMap;

import transportManagement.supportClasses.MyDate;
import transportManagement.supportClasses.TransportClass;

class Flight extends Transition
{
	private String dest;
	
	Flight( String orig, MyDate depart, String id, String de )
	{ super(orig, id, depart, new HashMap<TransportClass, TransportSection>()); }
	
	void addSection( TransportSection section ) { sections.put(section.getTransportClass(), section); }

	boolean hasCities( String o, String... d ) {
		if( o.equals(origin) && d[0].equals(dest) ) return true;
		
		return false;
	}
	
	@Override
	public String toString() {
		return "Flight " + ID + " traveling from " + origin + " to " + dest + " on " + departDate.toString() +
			   " with Flight Sections:\n\t\t\t" + sections.values().toString();
	}
	
	HashMap<TransportClass, TransportSection> getSections() { return sections; }

	public String getID() { return ID; }
}
