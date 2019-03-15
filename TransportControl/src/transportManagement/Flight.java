package transportManagement;

import java.util.HashMap;

class Flight extends Transition
{
	private String orig, dest, ID;
	private int year, month, day;
	private HashMap<SeatClass, TransportSection> flightSections;
	
	Flight( String or, int ye, int mo, int da, String id, String de ) {
		orig = or; dest = de; ID = id;
		year = ye; month = mo; day = da; 
		flightSections = new HashMap<SeatClass, TransportSection>();
	}
	
	void addSection( TransportSection section ) { flightSections.put(section.getSeatClass(), section); }

	boolean hasCities( String o, String... d ) {
		if( o.equals(orig) && d[0].equals(dest) ) return true;
		
		return false;
	}
	
	@Override
	public String toString() {
		return "Flight " + ID + " traveling from " + orig + " to " + dest + " on " + day + "/" + month + "/" + year +
			   " with Flight Sections:\n\t\t\t" + flightSections.values().toString();
	}
	
	HashMap<SeatClass, TransportSection> getSections() { return flightSections; }

	public String getID() { return ID; }
}
