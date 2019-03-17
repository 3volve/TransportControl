package transportManagement;

import java.util.ArrayList;
import java.util.HashMap;

import transportManagement.supportClasses.MyDate;

abstract class Transition {

	protected String origin, ID;
	protected MyDate departDate;
	protected HashMap<SeatClass, TransportSection> sections;
	
	protected Transition( String orig, String id, MyDate depart, HashMap<SeatClass, TransportSection> sect )
	{ origin = orig; ID = id; departDate = depart; sections = sect; }
	
	abstract boolean hasCities( String orig, String... dest );
	
	String getID() { return ID; }

	abstract void addSection(TransportSection section);

	HashMap<SeatClass, TransportSection> getSections() { return sections; }
	
	class DataClass {
		final String orig, ID;
		final TransportLine line;
		final ArrayList<String> dest;
		final MyDate departDate, arriveDate;
		
		DataClass( TransportLine l, String o, MyDate deD, MyDate arD, String id, String... de ) {
			line = l; orig = o; ID = id;
			departDate = deD; arriveDate = arD;
			
			dest = new ArrayList<String>();
			for( String d : de ) dest.add(d);
		}
	}
}
