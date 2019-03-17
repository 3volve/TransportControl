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
		final String lName, orig, ID;
		final ArrayList<String> dest;
		final MyDate departDate, arriveDate;
		final HashMap<String, TransportLine> lines;
		final HashMap<String, City> cities;
		
		DataClass( String n, String o, MyDate deD, MyDate arD, String id, HashMap<String, TransportLine> l, HashMap<String, City> c, String... de ) {
			lName = n; orig = o; ID = id;
			departDate = deD; arriveDate = arD;
			lines = l; cities = c;
			
			dest = new ArrayList<String>();
			for( String d : de ) dest.add(d);
		}
	}
}
